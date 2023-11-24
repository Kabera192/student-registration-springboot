package com.auca.studentregistration.service;

import com.auca.studentregistration.dto.SemesterDto;
import com.auca.studentregistration.entity.Semester;
import com.auca.studentregistration.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public boolean saveSemester(SemesterDto semester){
        Optional<Semester> semesterToSave = semesterRepository.findById(semester.getSemId());
        if(semesterToSave.isEmpty()){
            Semester sem = new Semester();
            sem.setSemId(semester.getSemId());
            sem.setSemName(semester.getName());
            sem.setStartDate(LocalDate.parse(semester.getStartDate()));
            sem.setEndDate(LocalDate.parse(semester.getEndDate()));
            semesterRepository.save(sem);
            return true;
        }
        return false;
    }
}
