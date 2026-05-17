package com.example.plantcare.mapper;

import com.example.plantcare.dto.DiseaseDto;
import com.example.plantcare.model.Disease;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiseaseMapper {
    DiseaseDto toDto(Disease disease);
    Disease toEntity(DiseaseDto diseaseDto);
}