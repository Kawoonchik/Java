package com.example.plantcare.mapper;

import com.example.plantcare.dto.PlantDto;
import com.example.plantcare.model.Plant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantMapper {

    PlantDto toDto(Plant plant);

    Plant toEntity(PlantDto plantDto);
}