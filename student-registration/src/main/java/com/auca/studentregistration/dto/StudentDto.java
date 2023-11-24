package com.auca.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String regNo;
    private String name;
    private String dateOfBirth;
    private String department;
}
