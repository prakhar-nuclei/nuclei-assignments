package com.nuclei.assignment2.controller;

import com.nuclei.assignment2.dto.StudentRequestDto;
import com.nuclei.assignment2.dto.StudentResponseDto;
import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;
import com.nuclei.assignment2.service.IStudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    private final IStudentService studentService;

    public StudentController( final IStudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StudentResponseDto> addStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {


        final StudentResponseDto studentResponseDto =
                studentService.addStudent(studentRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentResponseDto);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(
            @RequestParam(defaultValue = "NAME")
            final SortFeildEnum sortFeild,

            @RequestParam(defaultValue = "ASC")
            final SortDirectionEnum sortDirection
    ) {
        final List<StudentResponseDto> students =
                studentService.getAllStudents(
                        sortFeild,
                        sortDirection
                );

        return ResponseEntity.ok(students);
    }

}