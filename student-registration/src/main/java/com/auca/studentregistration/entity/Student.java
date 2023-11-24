package com.auca.studentregistration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_student")
public class Student {
    @Id
    @Column(name = "reg_number")
    private String regNo;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourse = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<StudentRegistration> studentRegistration = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department")
    private AcademicUnit department;
}
