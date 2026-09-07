package com.nuclei.assignment2.service.impl;

import com.nuclei.assignment2.dto.StudentRequestDto;
import com.nuclei.assignment2.dto.StudentResponseDto;
import com.nuclei.assignment2.entity.StudentEntity;
import com.nuclei.assignment2.enums.SortDirectionEnum;
import com.nuclei.assignment2.enums.SortFeildEnum;
import com.nuclei.assignment2.enums.StudentStatusEnum;
import com.nuclei.assignment2.mapper.IStudentMapper;
import com.nuclei.assignment2.model.Student;
import com.nuclei.assignment2.repo.StudentRepository;
import com.nuclei.assignment2.service.IStudentService;
import com.nuclei.assignment2.storage.IStudentStorage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(StudentServiceImpl.class);

    private final IStudentStorage studentStorage;
    private final IStudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(
            final IStudentStorage studentStorage,
            final IStudentMapper studentMapper,
            final StudentRepository studentRepository
    ) {
        this.studentStorage = studentStorage;
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDto addStudent(
            final StudentRequestDto studentRequestDto
    ) {

        final Student student =
                studentMapper.toModel(studentRequestDto);

        final Student addedStudent =
                studentStorage.addStudent(student);

        return studentMapper.toResponseDto(addedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents(
            final SortFeildEnum sortFeild,
            final SortDirectionEnum sortDirection
    ) {

        final List<Student> students = studentStorage.getAllStudents();

        Comparator<Student> comparator = sortFeild.getComparator();

        if (sortDirection == SortDirectionEnum.DESC) {
            comparator = comparator.reversed();
        }

        return students.stream()
                .sorted(comparator)
                .map(studentMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteStudent(final Integer rollNumber) {

        studentRepository.findByRollNumber(rollNumber)
                .ifPresent(studentEntity -> {

                    studentEntity.setStatus(StudentStatusEnum.DELETED);

                    studentRepository.save(studentEntity);
                });

        studentStorage.deleteByRollNumber(rollNumber);
    }

    @Override
    @Transactional
    public void saveStudentsToDatabase() {

        final List<Student> students =
                studentStorage.getAllStudents();

        final List<Integer> rollNumbers =
                students.stream()
                        .map(Student::getRollNumber)
                        .toList();

        final Map<Integer, StudentEntity> existingStudents =
                studentRepository.findByRollNumberIn(rollNumbers)
                        .stream()
                        .collect(Collectors.toMap(
                                StudentEntity::getRollNumber,
                                Function.identity()
                        ));

        final List<StudentEntity> studentEntities =             //N+1 problem resolved
                                                                // checking roll no. only one time
                students.stream()
                        .map(student -> {

                            final StudentEntity existingStudentEntity =
                                    existingStudents.get(student.getRollNumber());

                            if (existingStudentEntity != null) {

                                studentMapper.updateEntity(
                                        student,
                                        existingStudentEntity
                                );

                                existingStudentEntity.setStatus(
                                        StudentStatusEnum.ACTIVE
                                );

                                return existingStudentEntity;
                            }

                            final StudentEntity studentEntity =
                                    studentMapper.toEntity(student);

                            studentEntity.setStatus(
                                    StudentStatusEnum.ACTIVE
                            );

                            return studentEntity;
                        })
                        .toList();

        studentRepository.saveAll(studentEntities);
    }

    @PostConstruct
    public void loadStudentsFromDatabase() {

        studentRepository
                .findAllByStatus(StudentStatusEnum.ACTIVE)
                .stream()                     // calling this automatically to load
                .map(studentMapper::toModel)
                .forEach(studentStorage::addStudent);

        LOGGER.info("Students loaded successfully from database.");
    }
}