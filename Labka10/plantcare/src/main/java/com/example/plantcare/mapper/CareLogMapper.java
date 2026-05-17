package com.example.plantcare.mapper;

import com.example.plantcare.dto.CareLogDto;
import com.example.plantcare.model.CareLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CareLogMapper {
    @Mapping(source = "plant.id", target = "plantId")
    CareLogDto toDto(CareLog careLog);

    @Mapping(source = "plantId", target = "plant.id")
    CareLog toEntity(CareLogDto careLogDto);
}