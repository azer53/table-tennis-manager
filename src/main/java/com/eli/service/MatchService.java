package com.eli.service;

import com.eli.domain.Match;
import com.eli.domain.Player;
import com.eli.integration.frenoy.FrenoyConfig;
import com.eli.integration.frenoy.FrenoyMatches;
import com.eli.repository.MatchRepository;
import com.eli.service.errors.MatchWithoutPlayerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.main.java.com.eli.integration.GetMatchesResponse;
import src.main.java.com.eli.integration.IndividualMatchResultEntryType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Service Implementation for managing Match.
 */
@Service
@Transactional
public class MatchService {

    private final Logger log = LoggerFactory.getLogger(MatchService.class);

    private final MatchRepository matchRepository;
    private final PlayerService playerService;

    public MatchService(MatchRepository matchRepository, PlayerService playerService) {
        this.matchRepository = matchRepository;
        this.playerService = playerService;
    }

    /**
     * Save a match.
     *
     * @param match the entity to save
     * @return the persisted entity
     */
    public Match save(Match match) {
        log.debug("Request to save Match : {}", match);
        return matchRepository.save(match);
    }

    /**
     * Get all the matches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Match> findAll(Pageable pageable) {
        log.debug("Request to get all Matches");
        return matchRepository.findAll(pageable);
    }

    /**
     * Get one match by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Match findOne(Long id) {
        log.debug("Request to get Match : {}", id);
        return matchRepository.findOne(id);
    }

    /**
     * Delete the  match by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Match : {}", id);
        matchRepository.delete(id);
    }

    public List<Match> getMatches(int playWeek) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FrenoyConfig.class);
        FrenoyMatches request = context.getBean(FrenoyMatches.class);
        GetMatchesResponse response = request.getAllSintNiklaasMatches(playWeek);
        return parseMatches(response);
    }

    private List<Match> parseMatches(GetMatchesResponse response) {

        List<IndividualMatchResultEntryType> individualMatches = response.getTeamMatchesEntries().stream()
            .map(teamMatchEntryType -> teamMatchEntryType.getMatchDetails())
            .flatMap(teamMatchDetailsEntryType -> teamMatchDetailsEntryType.getIndividualMatchResults().stream())
            .collect(Collectors.toList());

        List<Match> matches = new ArrayList<>();

        for (IndividualMatchResultEntryType matchResult : individualMatches) {
            Match match = new Match();
            if (matchResult.getHomeSetCount() != null) {
                match.setHomePlayerSets(matchResult.getHomeSetCount().intValue());
            }
            if (matchResult.getAwaySetCount() != null) {
                match.setAwayPlayerSets(matchResult.getAwaySetCount().intValue());
            }
            try {
                match.setHomePlayer(playerService.getOrCreatePlayer(matchResult.getHomePlayerUniqueIndex()));
                match.setAwayPlayer(playerService.getOrCreatePlayer(matchResult.getAwayPlayerUniqueIndex()));
                matches.add(match);
            } catch (MatchWithoutPlayerException e) {
                log.debug("skipping match");
            }
        }
        return matches;

    }


}
