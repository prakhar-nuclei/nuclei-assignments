package com.nuclei.assignment2.dto;

import com.nuclei.assignment2.enums.CourseEnum;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDto {

    @NotBlank(message = "Full name is mandatory.")
    private String fullName;

    @NotNull(message = "Age is mandatory.")
    @Min(value = 1, message = "Age must be greater than 0.")
    private Integer age;

    @NotBlank(message = "Address is mandatory.")
    private String address;

    @NotNull(message = "Roll number is mandatory.")
    @Positive(message = "Roll number must be greater than 0.")
    private Integer rollNumber;

    @NotNull(message = "Courses are mandatory.")
    @Size(
            min = 4,
            max = 4,
            message = "Exactly 4 courses must be selected."
    )
    private Set<CourseEnum> courses;
}
