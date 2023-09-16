package com.colege.service;

import com.colege.entity.Student;
import com.colege.expection.CustomExpection;
import com.colege.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private static final String NF_MESSAGE = "student not found";
    private static final String NR_MESSAGE = "not result";

    private final StudentRepository repository;

    public void saveStudent(Student student) {
        repository.save(student);
    }
    @SneakyThrows
    public List<Student> findAllStudents() {
        List<Student> students = repository.findAll();
        if(students.isEmpty()) throw new CustomExpection(NR_MESSAGE, HttpStatus.NO_CONTENT);
        return students;
    }
    @SneakyThrows
    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        List<Student> students = repository.findAllBySchoolId(schoolId);
        if(students.isEmpty()) throw new CustomExpection(NF_MESSAGE, HttpStatus.NOT_FOUND);
        return students;
    }
}
