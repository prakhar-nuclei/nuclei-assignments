package com.nuclei.assignment2.enums;

import com.nuclei.assignment2.model.Student;

import java.util.Comparator;

public enum SortFeildEnum {

    NAME(
            Comparator.comparing(
                    Student::getFullName,
                    String.CASE_INSENSITIVE_ORDER
            )
    ),

    ROLL_NUMBER(
            Comparator.comparing(Student::getRollNumber)
    ),

    AGE(
            Comparator.comparing(Student::getAge)
    ),

    ADDRESS(
            Comparator.comparing(
                    Student::getAddress,
                    String.CASE_INSENSITIVE_ORDER
            )
    );

    private final Comparator<Student> comparator;

    SortFeildEnum(final Comparator<Student> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Student> getComparator() {
        return comparator;
    }
}