package com.colege.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Integer idStudent;
    private String firstname;
    private String lastname;
    private String email;
    private Integer schoolId;
}
