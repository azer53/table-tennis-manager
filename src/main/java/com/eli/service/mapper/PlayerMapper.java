package com.eli.service.mapper;

import com.eli.domain.*;
import com.eli.service.dto.PlayerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Player and its DTO PlayerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlayerMapper extends EntityMapper<PlayerDTO, Player> {

    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "homeMatches", ignore = true)
    @Mapping(target = "awayMatches", ignore = true)
    Player toEntity(PlayerDTO playerDTO);

    default Player fromId(Long id) {
        if (id == null) {
            return null;
        }
        Player player = new Player();
        player.setId(id);
        return player;
    }

    @Mappings({
        @Mapping(source = "awayMatches", target = "awayMatches"),
        @Mapping(source = "homeMatches", target = "homeMatches")
    })
    PlayerDTO toDto(Player player);
}
