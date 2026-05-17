package com.example.plantcare.mapper;

import com.example.plantcare.dto.SensorDataDto;
import com.example.plantcare.model.SensorData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorDataMapper {
    @Mapping(source = "plant.id", target = "plantId")
    SensorDataDto toDto(SensorData sensorData);

    @Mapping(source = "plantId", target = "plant.id")
    SensorData toEntity(SensorDataDto sensorDataDto);
}