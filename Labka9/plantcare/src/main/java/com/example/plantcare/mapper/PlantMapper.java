package com.example.plantcare.mapper;

import com.example.plantcare.dto.PlantDto;
import com.example.plantcare.model.Plant;
import com.example.plantcare.model.CareLog;
import com.example.plantcare.model.Disease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PlantMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "careLogIds", expression = "java(mapCareLogs(plant.getCareLogs()))")
    @Mapping(target = "diseaseIds", expression = "java(mapDiseases(plant.getDiseases()))")
    PlantDto toDto(Plant plant);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "careLogs", ignore = true)
    @Mapping(target = "diseases", ignore = true)
    Plant toEntity(PlantDto plantDto);

    default List<Long> mapCareLogs(List<CareLog> careLogs) {
        if (careLogs == null) return null;
        return careLogs.stream().map(CareLog::getId).collect(Collectors.toList());
    }

    default Set<Long> mapDiseases(Set<Disease> diseases) {
        if (diseases == null) return null;
        return diseases.stream().map(Disease::getId).collect(Collectors.toSet());
    }
}