package com.colege.service;

import com.colege.dto.StudentDTO;
import com.colege.entity.Student;
import com.colege.exception.CustomException;
import com.colege.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final static String NF_STUDENT = "Not found Student";
    private final static String AU_STUDENT = "Already use email user";

    public Flux<Student> getALl(){
        return repository.findAll()
                .switchIfEmpty(Mono.error(new CustomException(NF_STUDENT, HttpStatus.NO_CONTENT)));
    }
    public Mono<Student> save(Student student){
        Mono<Boolean> existsStudent = repository.existsByEmailAndIdStudentNot(student.getEmail(),student.getIdStudent());
        return existsStudent.flatMap(
                exists -> exists
                        ? Mono.error(new CustomException(AU_STUDENT,HttpStatus.BAD_REQUEST))
                        : repository.save(student)
        );
    }
    public Flux<Student> getAllByIdSchool(Integer idSchool){
        return repository.findAllBySchoolId(idSchool)
                .switchIfEmpty(Mono.error(new CustomException(NF_STUDENT,HttpStatus.NO_CONTENT)));
    }
}
