package com.nuclei.assignment2.storage;

import com.nuclei.assignment2.model.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Component
public class StudentStorage {

    private final Set<Student> students = new TreeSet<>();

    private final Map<Integer, Student> studentsByRollNumber = new HashMap<>();

    public void addStudent(Student student) {

        students.add(student);

        studentsByRollNumber.put(
                student.getRollNumber(),
                student
        );
    }

    public Student findByRollNumber(Integer rollNumber) {

        return studentsByRollNumber.get(rollNumber);
    }

    public Student deleteByRollNumber(Integer rollNumber) {

        Student student = studentsByRollNumber.remove(rollNumber);

        if (student != null) {
            students.remove(student);
        }

        return student;
    }

    public Set<Student> getAllStudents() {
        return new TreeSet<>(students);              // copy return ki taaki andr wala safe reh sake
    }
}
