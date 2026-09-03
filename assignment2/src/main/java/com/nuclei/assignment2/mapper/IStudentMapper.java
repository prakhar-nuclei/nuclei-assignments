package com.nuclei.assignment2.mapper;

import com.nuclei.assignment2.dto.StudentRequestDto;
import com.nuclei.assignment2.dto.StudentResponseDto;
import com.nuclei.assignment2.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStudentMapper {

    Student toModel(StudentRequestDto studentRequestDto);

    StudentResponseDto toResponseDto(Student student);
}