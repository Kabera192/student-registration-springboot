package com.auca.studentregistration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_student_reg")
public class StudentRegistration {

    @Id
    @Column(name = "reg_id")
    private String regId;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
}
