package com.colege.service;

import com.colege.entity.Student;
import com.colege.expection.CustomExpection;
import com.colege.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ActiveProfiles("test")
class StudentServiceTest {

    private StudentRepository repository = Mockito.mock(StudentRepository.class);
    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService(repository);
    }

    @Test
    void verify_that_entity_send_repo_is_equal_to_service() {
        // TODO: given
        Student student = Student.builder()
                .id(1).email("elias@gmail.com").schoolId(1).lastname("tagle").firstname("elias").build();
        // TODO: when
        service.saveStudent(student);
        // TODO: then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(repository).save(studentArgumentCaptor.capture());
        Student captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(student);
    }

    @Test
    void verify_that_throw_expection_findAllStudents() {
        // TODO: given
        // the entity is empty
        // TODO: when
        Throwable exception = assertThrows(CustomExpection.class, () -> {
            service.findAllStudents();
        });
        // TODO: then
        assertEquals("not result", exception.getMessage());
    }

    @Test
    void verify_the_content_findAllStudents() {
        // TODO: given
        List<Student> students = Arrays.asList(
            new Student(1,"elias","zevallos","elias@gmail.com",1),
                new Student(2,"jafet","tagle","jafet@gmail.com",2)
        );
        Mockito.when(repository.findAll()).thenReturn(students);
        // TODO: when
        boolean existStudents = service.findAllStudents().isEmpty();
        // TODO: then
        assertFalse(existStudents);
    }

    @Test
    void return_findAllStudentsBySchool() {
        // TODO: given
        List<Student> students = Arrays.asList(
                new Student(1,"elias","zevallos","elias@gmail.com",1)
                //new Student(2,"jafet","tagle","jafet@gmail.com",2)
        );
        Mockito.when(repository.findAllBySchoolId(1)).thenReturn(students);
        // TODO: when
        boolean existStudents = service.findAllStudentsBySchool(1).isEmpty();
        // TODO: then
        assertFalse(existStudents);
    }
}