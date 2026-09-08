package com.nuclei.assignment2.dto;

import com.nuclei.assignment2.enums.CourseEnum;
import lombok.*;


import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {

    private String fullName;

    private Integer age;

    private String address;

    private Integer rollNumber;

    private Set<CourseEnum> courses;
}
