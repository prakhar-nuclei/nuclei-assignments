package com.nuclei.assignment2.model;

import com.nuclei.assignment2.enums.CourseEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.Set;

@Builder
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

    public Student(
            String fullName,
            Integer age,
            String address,
            Integer rollNumber,
            Set<CourseEnum> courses
    ) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.rollNumber = rollNumber;
        this.courses = courses;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public Integer getRollNumber() {
        return rollNumber;
    }

    public Set<CourseEnum> getCourses() {
        return courses;
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