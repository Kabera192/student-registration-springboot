package com.auca.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String courseCode;
    private String courseName;
    private String description;
    private String department;
    private List<SemesterDto> semesters;
}
