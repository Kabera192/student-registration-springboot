package com.auca.studentregistration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_academic_unit")
public class AcademicUnit {
    @Id
    @Column(name = "unit_code")
    private String code;

    @Column(name = "unit_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_unit")
    private EAcademicUnit academicUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    private AcademicUnit program;

    @OneToMany(mappedBy = "program")
    private List<AcademicUnit> faculties;

    @ManyToOne(fetch = FetchType.EAGER)
    private AcademicUnit faculty;

    @OneToMany(mappedBy = "faculty")
    private List<AcademicUnit> departments;

    @OneToMany(mappedBy = "department")
    private List<Student> students;

    @OneToMany(mappedBy = "department")
    private List<Course> courses = new ArrayList<>();
}
