package com.example.plantcare.mapper;

import com.example.plantcare.dto.CareLogDto;
import com.example.plantcare.model.CareLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CareLogMapper {
    CareLogDto toDto(CareLog careLog);
    CareLog toEntity(CareLogDto careLogDto);
}