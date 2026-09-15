package com.nuclei.assignment2.service;

import com.nuclei.assignment2.dto.StudentRequestDto;
import com.nuclei.assignment2.dto.StudentResponseDto;
import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;

import java.util.List;

public interface IStudentService {

    StudentResponseDto addStudent(
            final StudentRequestDto studentRequestDto
    );

    List<StudentResponseDto> getAllStudents(
            final SortFeildEnum sortFeild,
            final SortDirectionEnum sortDirection
    );
    void saveStudentsToDatabase();
    void deleteStudent(Integer rollNumber);
}