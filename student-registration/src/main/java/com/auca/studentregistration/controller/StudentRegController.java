package com.auca.studentregistration.controller;
import com.auca.studentregistration.dto.*;
import com.auca.studentregistration.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/auca")
@RequiredArgsConstructor
public class StudentRegController {

    private final AcademicUnitService unitService;
    private final StudentService studentService;
    private final SemesterService semesterService;
    private final StudentRegistrationService registrationService;
    private final CourseService courseService;

    @PostMapping("/create-student")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto){
        if(studentService.saveStudent(studentDto)){
            return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Student already exists", HttpStatus.OK);
    }

    @PostMapping("/create-academic-unit")
    public ResponseEntity<?> saveAcademicUnit(@RequestBody AcademicUnitDto academicUnit){
        if(unitService.saveAcademicUnit(academicUnit)){
            return new ResponseEntity<>("Unit: " + academicUnit.getName() + " saved!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Unit already saved", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create-semester")
    public ResponseEntity<?> saveSemester(@RequestBody SemesterDto semester){
        if(semesterService.saveSemester(semester)){
            return new ResponseEntity<>("Semester "+semester.getName()+" saved", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Semester already saved", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all-units")
    public ResponseEntity<?> findAllUnits(){
        return new ResponseEntity<>(unitService.findAllUnits(), HttpStatus.OK);
    }

    @PostMapping("/student-registration")
    public ResponseEntity<?> saveStudentRegistration(@RequestBody RegistrationDto reg){
        if(registrationService.saveStudentRegistration(reg)){
            return new ResponseEntity<>(reg.getRegId() + " Saved successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Registration already exists OR Student / Semester not known", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/student-semester/{semester}")
    public ResponseEntity<?> getStudentsBySemester(@PathVariable String semester){
        List<RegistrationDto> students = registrationService.getStudentsBySemester(semester);
        if(students != null){
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>("No students found for " + semester + " Semester", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/student-sem-dept")
    public ResponseEntity<?> getStudentBySemesterAndDept(@RequestParam("semester") String semester,
                                                         @RequestParam("dept") String dept){
        List<RegistrationDto> students = registrationService.getStudentsBySemesterAndDepartment(semester, dept);
        if(students != null){
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>("Semester: " + semester + " OR Department: " + dept + " not found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/saveCourse")
    public ResponseEntity<?> saveCourse(@RequestBody CourseDto courseDto){
        if(courseService.saveCourse(courseDto)){
            return new ResponseEntity<>(courseDto.getCourseCode() + " "+courseDto.getCourseName()+" saved!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Course code already exists in the system", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/saveCourseToSem")
    public ResponseEntity<?> addCourseToSem(@RequestParam("course") String courseCode,
                                            @RequestParam("semester") String semester){
        if(courseService.saveCourseToSem(courseCode, semester)){
            return new ResponseEntity<>("Course added to semester successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Course / Sem does not exist", HttpStatus.NOT_FOUND);
    }
}
