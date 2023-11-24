package com.auca.studentregistration.service;

import com.auca.studentregistration.dto.StudentDto;
import com.auca.studentregistration.entity.Student;
import com.auca.studentregistration.repository.AcademicUnitRepository;
import com.auca.studentregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final AcademicUnitRepository academicUnitRepository;

    public boolean saveStudent(StudentDto student){
        Optional<Student> s = studentRepository.findById(student.getRegNo());
        if(s.isEmpty()){
            Student studentToSave = new Student();
            if(student.getRegNo() != null)
                studentToSave.setRegNo(student.getRegNo());
            if(student.getName() != null)
                studentToSave.setName(student.getName());
            if(student.getDateOfBirth() != null)
                studentToSave.setDateOfBirth(LocalDate.parse(student.getDateOfBirth()));
            if(student.getDepartment() != null)
                studentToSave.setDepartment(academicUnitRepository.findByName(student.getDepartment()));

            studentRepository.save(studentToSave);
            return true;
        }
        return false;
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }
}
