package com.nuclei.assignment2.controller;

import com.nuclei.assignment2.dto.StudentDto;
import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;
import com.nuclei.assignment2.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(
            path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public StudentDto addStudent(@Valid @RequestBody StudentDto studentDto) {


       return studentService.addStudent(studentDto);
    }

    @GetMapping(
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<StudentDto> getAllStudents(
            @RequestParam SortFeildEnum sortFeild,
            @RequestParam SortDirectionEnum sortDirection
    ) {
        return studentService.getAllStudents(sortFeild, sortDirection);
    }

}