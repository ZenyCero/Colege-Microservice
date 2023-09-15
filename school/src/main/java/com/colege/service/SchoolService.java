package com.colege.service;

import com.colege.client.StudentClient;
import com.colege.dto.SchoolResponseDTO;
import com.colege.entity.School;
import com.colege.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;
    private final StudentClient client;

    public void saveSchool(School school) {
        repository.save(school);
    }

    public List<School> findAllSchools() {
        return repository.findAll();
    }

    public SchoolResponseDTO findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var students = client.findAllStudentsBySchool(schoolId);
        return SchoolResponseDTO.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}
