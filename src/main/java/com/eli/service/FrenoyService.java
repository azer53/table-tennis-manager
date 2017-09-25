package com.eli.service;

import com.eli.domain.Player;
import com.eli.integration.frenoy.FrenoyConfig;
import com.eli.integration.frenoy.FrenoyPlayers;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import src.main.java.com.eli.integration.GetMembersResponse;
import src.main.java.com.eli.integration.MemberEntryType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colpaertel on 25/09/2017.
 */
@Service
public class FrenoyService {

    public List<Player> getSintNiklaasPlayers() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FrenoyConfig.class);
        FrenoyPlayers players = context.getBean(FrenoyPlayers.class);
        GetMembersResponse response = players.getAllSintNiklaasMembers();

        List<Player> parsedPlayers = new ArrayList<>();
        for (MemberEntryType member : response.getMemberEntries()) {
            Player player = new Player();
            player.setFirstName(member.getFirstName());
            player.setLastName(member.getLastName());
            player.setRanking(member.getRanking());

            parsedPlayers.add(player);
        }

        return parsedPlayers;
    }
}
