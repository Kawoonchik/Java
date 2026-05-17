package com.example.plantcare.mapper;

import com.example.plantcare.dto.SensorDataDto;
import com.example.plantcare.model.SensorData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorDataMapper {
    SensorDataDto toDto(SensorData sensorData);
    SensorData toEntity(SensorDataDto sensorDataDto);
}