package com.nuclei.assignment2.repo;

import com.nuclei.assignment2.entity.StudentEntity;
import com.nuclei.assignment2.enums.StudentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByRollNumberAndStatus(
            Integer rollNumber,
            StudentStatusEnum status
    );
    List<StudentEntity> findAllByStatus(StudentStatusEnum status);
    List<StudentEntity> findByRollNumberInAndStatus(
            List<Integer> rollNumbers,
            StudentStatusEnum status
    );
}