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
@Table(name = "tbl_course")
public class Course {
    @Id
    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourse = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Semester> semesters = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department")
    private AcademicUnit department;
}
