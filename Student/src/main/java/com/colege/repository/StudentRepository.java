package com.colege.repository;

import com.colege.entity.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {
    Flux<Student> findAllBySchoolId(int idSchool);
    Mono<Boolean> existsByEmailAndIdStudentNot(String email, Integer idStudent);
}
