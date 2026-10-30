package com.examplegame.GameService.model.dto;

import com.examplegame.GameService.model.entity.GameEntity;

public class GameMapper {

    public static GameDto toDto(GameEntity entity) {
        return new GameDto(
                entity.getId(),
                entity.getName(),
                entity.getImage(),
                entity.getDate(),
                entity.getTime(),
                entity.getType(),
                entity.getLevel(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getMaster().getId()
        );
    }


    public static GameEntity toEntity(GameCreateDto dto) {
        return GameEntity.builder()
                .name(dto.name())
                .image(dto.image())
                .date(dto.date())
                .time(dto.time())
                .type(dto.type())
                .level(dto.level())
                .description(dto.description())
                .build();
    }

}
