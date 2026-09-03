package com.nuclei.assignment2.storage;

import com.nuclei.assignment2.model.Student;

import java.util.List;

public interface IStudentStorage {


    Student addStudent(Student student);

    Student findByRollNumber(Integer rollNumber);

    Student deleteByRollNumber(Integer rollNumber);

    List<Student> getAllStudents();
}

