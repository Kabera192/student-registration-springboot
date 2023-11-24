package com.auca.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDto {
    private String semId;
    private String name;
    private String startDate;
    private String endDate;
}
