package com.colege.service;

import com.colege.client.StudentClient;
import com.colege.dto.SchoolResponseDTO;
import com.colege.entity.School;
import com.colege.expection.CustomExpection;
import com.colege.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final static String NF_MESSAGE = "school not found";
    private final static String NC_MESSAGE = "school not contend";

    private final SchoolRepository repository;
    private final StudentClient client;

    public void saveSchool(School school) {
        repository.save(school);
    }
    @SneakyThrows
    public List<School> findAllSchools() {
        List<School> schools = repository.findAll();
        if(schools.isEmpty()) throw new CustomExpection(NC_MESSAGE, HttpStatus.NO_CONTENT);
        return schools;
    }
    @SneakyThrows
    public SchoolResponseDTO findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElseThrow(
                        ()->new CustomExpection(NF_MESSAGE,HttpStatus.NOT_FOUND)
                );
        var students = client.findAllStudentsBySchool(schoolId);
        return SchoolResponseDTO.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}
