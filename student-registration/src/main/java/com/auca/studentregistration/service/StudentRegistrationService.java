package com.auca.studentregistration.service;

import com.auca.studentregistration.dto.RegistrationDto;
import com.auca.studentregistration.entity.AcademicUnit;
import com.auca.studentregistration.entity.Semester;
import com.auca.studentregistration.entity.Student;
import com.auca.studentregistration.entity.StudentRegistration;
import com.auca.studentregistration.repository.AcademicUnitRepository;
import com.auca.studentregistration.repository.SemesterRepository;
import com.auca.studentregistration.repository.StudentRegistrationRepository;
import com.auca.studentregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentRegistrationService {

    private final StudentRegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;
    private final AcademicUnitRepository academicUnitRepository;

    public boolean saveStudentRegistration(RegistrationDto registrationDto) {
        Optional<StudentRegistration> reg = registrationRepository.findById(registrationDto.getRegId());
        if(reg.isEmpty()){
            StudentRegistration registration = new StudentRegistration();
            registration.setRegId(registrationDto.getRegId());
            Student s = studentRepository.findById(registrationDto.getStudentId()).orElse(null);
            if(s == null){
                return false;
            }else{
                registration.setStudent(s);
            }
            registration.setRegDate(LocalDate.parse(registrationDto.getRegDate()));
            Semester sem = semesterRepository.findById(registrationDto.getSemesterId()).orElse(null);
            if(sem == null){
                return false;
            }else{
                registration.setSemester(sem);
            }
            registrationRepository.save(registration);
            return true;
        }
        return false;
    }

    public List<RegistrationDto> getStudentsBySemester(String semester){
        Semester sem = semesterRepository.findById(semester).orElse(null);
        if(sem != null){
            List<StudentRegistration> students = registrationRepository.findBySemester(sem);
            List<RegistrationDto> registrationDtos = new ArrayList<>();
            for(StudentRegistration s : students){
                RegistrationDto reg = new RegistrationDto();
                reg.setRegId(s.getRegId());
                reg.setRegDate(s.getRegDate().toString());
                reg.setStudentId(s.getStudent().getName());
                reg.setSemesterId(s.getSemester().getSemName());
                registrationDtos.add(reg);
            }
            return registrationDtos;
        }
        return null;
    }

    public List<RegistrationDto> getStudentsBySemesterAndDepartment(String semester, String department){
        Semester sem = semesterRepository.findById(semester).orElse(null);
        AcademicUnit dept = academicUnitRepository.findById(department).orElse(null);
        if(sem != null && dept != null){
            List<StudentRegistration> students = registrationRepository.findBySemester(sem);
            List<RegistrationDto> registrationDtos = new ArrayList<>();
            for(StudentRegistration s : students){
                if(s.getStudent().getDepartment().getName().equals(dept.getName())) {
                    RegistrationDto reg = new RegistrationDto();
                    reg.setRegId(s.getRegId());
                    reg.setRegDate(s.getRegDate().toString());
                    reg.setStudentId(s.getStudent().getName());
                    reg.setSemesterId(s.getSemester().getSemName());
                    registrationDtos.add(reg);
                }
            }
            return registrationDtos;
        }
        return null;
    }
}
