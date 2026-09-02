package com.nuclei.assignment2.controller;

import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;
import com.nuclei.assignment2.model.Student;
import com.nuclei.assignment2.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(
            @RequestParam SortFeildEnum sortFeild,
            @RequestParam SortDirectionEnum sortDirection
    ) {
        return studentService.getAllStudents(sortFeild, sortDirection);
    }


}