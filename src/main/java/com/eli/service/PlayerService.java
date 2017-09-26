package com.eli.service;

import com.eli.domain.Player;
import com.eli.integration.frenoy.FrenoyConfig;
import com.eli.integration.frenoy.FrenoyPlayers;
import com.eli.repository.PlayerRepository;
import com.eli.service.errors.MatchWithoutPlayerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import src.main.java.com.eli.integration.GetMembersResponse;
import src.main.java.com.eli.integration.MemberEntryType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Service Implementation for managing Player.
 */
@Service
@Transactional
public class PlayerService {

    private final Logger log = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Save a player.
     *
     * @param player the entity to save
     * @return the persisted entity
     */
    public Player save(Player player) {
        log.debug("Request to save Player : {}", player);
        return playerRepository.save(player);
    }

    /**
     * Get all the players.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Player> findAll(Pageable pageable) {
        log.debug("Request to get all Players");
        return playerRepository.findAll(pageable);
    }

    /**
     * Get one player by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Player findOne(Long id) {
        log.debug("Request to get Player : {}", id);
        return playerRepository.findOne(id);
    }

    /**
     * Delete the  player by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Player : {}", id);
        playerRepository.delete(id);
    }

    public List<Player> getSintNiklaasPlayers() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FrenoyConfig.class);
        FrenoyPlayers players = context.getBean(FrenoyPlayers.class);
        GetMembersResponse response = players.getAllSintNiklaasMembers();
        return parsePlayers(response);
    }

    private List<Player> parsePlayers(GetMembersResponse response) {
        List<Player> parsedPlayers = new ArrayList<>();
        for (MemberEntryType member : response.getMemberEntries()) {
            Player player = new Player();
            player.setFirstName(member.getFirstName());
            player.setLastName(member.getLastName());
            player.setRanking(member.getRanking());
            player.setFrenoyId(String.valueOf(member.getUniqueIndex()));
            player.setId(member.getUniqueIndex().longValue());

            parsedPlayers.add(player);
        }

        return parsedPlayers;
    }

    public Player getOrCreatePlayer(List<BigInteger> playerIndexes) throws MatchWithoutPlayerException {
        if ((CollectionUtils.isEmpty(playerIndexes))) {
            throw new MatchWithoutPlayerException("no player index found in match");
        }

        BigInteger playerIndex = playerIndexes.get(0);
        Optional<Player> player = playerRepository.findByFrenoyId(playerIndex.toString());
        if (player.isPresent()) {
            return player.get();
        } else {
            Player frenoyPlayer = getPlayerFromFrenoy(playerIndex).get(0);
            playerRepository.save(frenoyPlayer);
            return frenoyPlayer;
        }
    }

    private List<Player> getPlayerFromFrenoy(BigInteger playerIndex) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FrenoyConfig.class);
        FrenoyPlayers request = context.getBean(FrenoyPlayers.class);
        GetMembersResponse response = request.getMember(playerIndex);
        return parsePlayers(response);
    }
}
