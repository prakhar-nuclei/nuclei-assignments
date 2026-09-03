package com.nuclei.assignment2.service;

import com.nuclei.assignment2.dto.StudentDto;
import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;
import com.nuclei.assignment2.exception.DuplicateRollNumberException;
import com.nuclei.assignment2.mapper.StudentMapper;
import com.nuclei.assignment2.model.Student;
import com.nuclei.assignment2.storage.StudentStorage;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


@Service
public class StudentService {



    private final StudentStorage studentStorage ;


    public StudentService(final StudentStorage studentStorage) {
        this.studentStorage = studentStorage;
    }

    public StudentDto addStudent (StudentDto studentDto) {

        Student student = StudentMapper.toModel(studentDto);
        Integer rollNumber = student.getRollNumber();

        if (studentStorage.existsByRollNumber(rollNumber)) {
            throw new DuplicateRollNumberException(
                    "Student with roll number " + rollNumber + " already exists."
            );
        }

        studentStorage.addStudent(student);

        return StudentMapper.toDto(student);
    }

    public List<StudentDto> getAllStudents(
            SortFeildEnum sortFeild,
            SortDirectionEnum sortDirection
    ) {

        Set<Student> students = studentStorage.getAllStudents();

        Comparator<Student> comparator = sortFeild.getComparator();

        if (sortDirection == SortDirectionEnum.DESC) {
            comparator = comparator.reversed();
        }

        return students.stream()
                .sorted(comparator)
                .map(StudentMapper::toDto)
                .toList();                     // copy original treeset not changing it
    }

}
