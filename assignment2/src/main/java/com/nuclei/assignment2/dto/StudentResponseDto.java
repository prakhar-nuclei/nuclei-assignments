package com.nuclei.assignment2.dto;

import com.nuclei.assignment2.enums.CourseEnum;

import java.util.Set;

public class StudentResponseDto {

    private String fullName;

    private Integer age;

    private String address;

    private Integer rollNumber;

    private Set<CourseEnum> courses;
}
