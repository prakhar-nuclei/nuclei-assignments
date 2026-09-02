package com.nuclei.assignment2.service;

import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;
import com.nuclei.assignment2.exception.DuplicateRollNumberException;
import com.nuclei.assignment2.model.Student;
import com.nuclei.assignment2.storage.StudentStorage;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


@Service
public class StudentService {

    private final StudentStorage studentStorage;

    public StudentService(StudentStorage studentStorage) {
        this.studentStorage = studentStorage;
    }

    public void addStudent(Student student) {

        Integer rollNumber = student.getRollNumber();

        Student existingStudent =
                studentStorage.findByRollNumber(rollNumber);

        if (existingStudent != null) {
            throw new DuplicateRollNumberException(
                    "Student with roll number " + rollNumber + " already exists."
            );
        }

        studentStorage.addStudent(student);
    }

    public List<Student> getAllStudents(
            SortFeildEnum sortFeild,
            SortDirectionEnum sortDirection
    ) {

        Set<Student> students = studentStorage.getAllStudents();

        Comparator<Student> comparator = switch (sortFeild) {

            case NAME ->
                    Comparator.comparing(
                            Student::getFullName,
                            String.CASE_INSENSITIVE_ORDER
                    );

            case ROLL_NUMBER ->                                      // by default asc mai sort hota hai
                    Comparator.comparing(Student::getRollNumber);

            case AGE ->
                    Comparator.comparing(Student::getAge);

            case ADDRESS ->
                    Comparator.comparing(
                            Student::getAddress,
                            String.CASE_INSENSITIVE_ORDER
                    );
        };

        if (sortDirection == SortDirectionEnum.DESC) {
            comparator = comparator.reversed();
        }

        return students.stream()
                .sorted(comparator)
                .toList();                     // copy original treeset not changing it
    }


}
