package com.nuclei.assignment2.mapper;

import com.nuclei.assignment2.dto.StudentDto;
import com.nuclei.assignment2.model.Student;

import java.util.HashSet;

public final class StudentMapper {

    private StudentMapper() {
    }

    public static Student toModel(StudentDto studentDto) {

        return Student.builder()
                .fullName(studentDto.getFullName())
                .age(studentDto.getAge())
                .address(studentDto.getAddress())
                .rollNumber(studentDto.getRollNumber())
                .courses(new HashSet<>(studentDto.getCourses()))
                .build();
    }

    public static StudentDto toDto(Student student) {

        return StudentDto.builder()
                .fullName(student.getFullName())
                .age(student.getAge())
                .address(student.getAddress())
                .rollNumber(student.getRollNumber())
                .courses(new HashSet<>(student.getCourses()))
                .build();
    }
}