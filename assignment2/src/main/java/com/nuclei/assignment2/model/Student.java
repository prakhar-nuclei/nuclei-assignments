package com.nuclei.assignment2.model;

import com.nuclei.assignment2.enums.CourseEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
public class Student implements Comparable<Student> {

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

    public Student(Student student) {
        this.fullName = student.fullName;
        this.age = student.age;                      //copy constructor
        this.address = student.address;
        this.rollNumber = student.rollNumber;
        this.courses = new HashSet<>(student.courses);
    }

    @Override
    public int compareTo(Student otherStudent) {

        int nameComparison =
                this.fullName.compareToIgnoreCase(otherStudent.fullName);
                                                                              // natural sorting comparable ki
        if (nameComparison != 0) {
            return nameComparison;
        }

        return this.rollNumber.compareTo(otherStudent.rollNumber);
    }
}