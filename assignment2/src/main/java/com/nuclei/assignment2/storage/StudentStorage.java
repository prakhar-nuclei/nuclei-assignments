package com.nuclei.assignment2.storage;

import com.nuclei.assignment2.exception.StudentNotFoundException;
import com.nuclei.assignment2.model.Student;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class StudentStorage {

    private final Set<Student> students = new TreeSet<>();


    public void addStudent(Student student) {

        students.add(student);

    }

    public Student findByRollNumber(Integer rollNumber) {

        return students.stream()
                .filter(student ->
                        student.getRollNumber().equals(rollNumber)
                )
                .findFirst()
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student with roll number "
                                        + rollNumber
                                        + " does not exist."
                        )
                );
    }

    public boolean existsByRollNumber(Integer rollNumber) {

        return students.stream()
                .anyMatch(student ->
                        student.getRollNumber().equals(rollNumber)
                );
    }

    public Student deleteByRollNumber(Integer rollNumber) {

        Student student = findByRollNumber(rollNumber);
        students.remove(student);

        return student;
    }

    public Set<Student> getAllStudents() {
        return students.stream()
                .map(Student::new)
                .collect(Collectors.toCollection(TreeSet::new));             // deep copy return ki taaki andr wala safe reh sake
    }
}
