package com.colege.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @Column("id_student")
    private Integer idStudent;
    @Column("first_name")
    private String firstname;
    @Column("last_name")
    private String lastname;
    private String email;
    @Column("school_id")
    private Integer schoolId;
}
