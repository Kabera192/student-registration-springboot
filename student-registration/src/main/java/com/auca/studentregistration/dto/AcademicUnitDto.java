package com.auca.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicUnitDto {
    private String code;
    private String name;
    private String unitType;
    private String parentName;
}
