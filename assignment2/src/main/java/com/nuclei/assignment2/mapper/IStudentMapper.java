package com.nuclei.assignment2.mapper;

import com.nuclei.assignment2.dto.StudentRequestDto;
import com.nuclei.assignment2.dto.StudentResponseDto;
import com.nuclei.assignment2.entity.StudentEntity;
import com.nuclei.assignment2.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IStudentMapper {

    Student toModel(StudentRequestDto studentRequestDto);

    StudentResponseDto toResponseDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    StudentEntity toEntity(Student student);

    Student toModel(StudentEntity studentEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntity(
            Student student,
            @MappingTarget StudentEntity studentEntity
    );
}