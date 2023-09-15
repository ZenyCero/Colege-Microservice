package com.colege.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolResponseDTO {

    private String name;
    private String email;
    List<Student> students;
}
