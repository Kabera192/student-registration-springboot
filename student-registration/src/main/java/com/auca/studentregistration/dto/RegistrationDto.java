package com.auca.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private String regId;
    private String regDate;
    private String studentId;
    private String semesterId;
}
