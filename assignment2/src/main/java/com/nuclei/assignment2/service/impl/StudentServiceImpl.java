package com.nuclei.assignment2.service.impl;

import com.nuclei.assignment2.dto.StudentRequestDto;
import com.nuclei.assignment2.dto.StudentResponseDto;
import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;
import com.nuclei.assignment2.mapper.IStudentMapper;
import com.nuclei.assignment2.model.Student;
import com.nuclei.assignment2.service.IStudentService;
import com.nuclei.assignment2.storage.IStudentStorage;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private final IStudentStorage studentStorage;
    private final IStudentMapper studentMapper;

    public StudentServiceImpl(
            final IStudentStorage studentStorage,
            final IStudentMapper studentMapper
    ) {
        this.studentStorage = studentStorage;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponseDto addStudent(
            final StudentRequestDto studentRequestDto
    ) {

        final Student student =
                studentMapper.toModel(studentRequestDto);

        final Student addedStudent =
                studentStorage.addStudent(student);

        return studentMapper.toResponseDto(addedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents(
            final SortFeildEnum sortFeild,
            final SortDirectionEnum sortDirection
    ) {

        final List<Student> students = studentStorage.getAllStudents();

        Comparator<Student> comparator = sortFeild.getComparator();

        if (sortDirection == SortDirectionEnum.DESC) {
            comparator = comparator.reversed();
        }

        return students.stream()
                .sorted(comparator)
                .map(studentMapper::toResponseDto)
                .toList();
    }
}