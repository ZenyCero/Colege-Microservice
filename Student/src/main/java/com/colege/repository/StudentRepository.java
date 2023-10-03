package com.colege.repository;

import com.colege.entity.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {
    List<Student> findAllBySchoolId(int schoolId);
}
