package com.nuclei.assignment2.storage.impl;

import com.nuclei.assignment2.exception.DuplicateRollNumberException;
import com.nuclei.assignment2.exception.StudentNotFoundException;
import com.nuclei.assignment2.model.Student;
import com.nuclei.assignment2.storage.IStudentStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StudentStorageImpl implements IStudentStorage {

    private final ConcurrentHashMap<Integer, Student> students =
            new ConcurrentHashMap<>();

    @Override
    public Student addStudent(Student student) {

        Student existingStudent = students.putIfAbsent(
                student.getRollNumber(),
                student
        );

        if (existingStudent != null) {
            throw new DuplicateRollNumberException(
                    "Student with roll number "
                            + student.getRollNumber()
                            + " already exists."
            );
        }

        return new Student(student);
    }

    @Override
    public Student findByRollNumber(Integer rollNumber) {

        Student student = students.get(rollNumber);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with roll number "
                            + rollNumber
                            + " does not exist."
            );
        }

        return new Student(student);
    }

    @Override
    public Student deleteByRollNumber(Integer rollNumber) {

        Student student = students.remove(rollNumber);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with roll number "
                            + rollNumber
                            + " does not exist."
            );
        }

        return new Student(student);
    }

    @Override
    public List<Student> getAllStudents() {

        return students.values()
                .stream()
                .map(Student::new)
                .toList();
    }
}