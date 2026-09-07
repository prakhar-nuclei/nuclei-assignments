package com.nuclei.assignment2.entity;

import com.nuclei.assignment2.enums.CourseEnum;
import com.nuclei.assignment2.enums.StudentStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer rollNumber;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String address;

    @ElementCollection(targetClass = CourseEnum.class,
            fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "course")
    private Set<CourseEnum> courses;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatusEnum status;
}