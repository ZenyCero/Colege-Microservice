package com.colege.repository;

import com.colege.entity.Student;
import com.colege.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void exists_student_by_school() {
        // given
        Student student = Student
                .builder()
                .id(1)
                .email("elias@gmail.com")
                .firstname("elias")
                .lastname("tagle")
                .schoolId(1)
                .build();
        // when
        studentRepository.save(student);
        boolean exist = studentRepository.findAllBySchoolId(1).isEmpty();
        // then
        assertFalse(exist);
    }

    @Test
    void not_exists_student_by_school() {
        // given
        Student student = Student
                .builder()
                .id(1)
                .email("elias@gmail.com")
                .firstname("elias")
                .lastname("tagle")
                .schoolId(1)
                .build();
        // when
        studentRepository.save(student);
        boolean exist = studentRepository.findAllBySchoolId(2).isEmpty();
        // then
        assertTrue(exist);
    }
}