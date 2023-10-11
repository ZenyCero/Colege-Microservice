package com.colege.controller;

import com.colege.dto.SchoolResponseDTO;
import com.colege.entity.School;
import com.colege.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody School school) {
        service.saveSchool(school);
    }
    @GetMapping("/{id}")
    public Optional<School> findById(@PathVariable Integer id){
        return service.findById(id);
    }
    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(service.findAllSchools());
    }
    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<SchoolResponseDTO> findAllSchools(@PathVariable("school-id") Integer schoolId) {
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));
    }
}
