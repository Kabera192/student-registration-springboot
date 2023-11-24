package com.auca.studentregistration.service;

import com.auca.studentregistration.dto.CourseDto;
import com.auca.studentregistration.entity.Course;
import com.auca.studentregistration.entity.Semester;
import com.auca.studentregistration.repository.AcademicUnitRepository;
import com.auca.studentregistration.repository.CourseRepository;
import com.auca.studentregistration.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.CheckedOutputStream;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final AcademicUnitRepository academicUnitRepository;
    private final SemesterRepository semesterRepository;

    public boolean saveCourse(CourseDto courseDto){
        Optional<Course> courseToSave = courseRepository.findById(courseDto.getCourseCode());

        if(courseToSave.isEmpty()){
            Course course = Course.builder()
                            .courseCode(courseDto.getCourseCode())
                            .courseName(courseDto.getCourseName())
                            .description(courseDto.getDescription())
                            .department(academicUnitRepository.findByName(courseDto.getDepartment()))
                    .build();
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    public boolean saveCourseToSem(String courseCode, String semester){
        Optional<Course> course = courseRepository.findById(courseCode);
        Optional<Semester> sem = semesterRepository.findById(semester);

        if(course.isEmpty() || sem.isEmpty()){
            return false;
        }
        Course courseToAdd = course.get();
        Semester semToAdd = sem.get();

        List<Semester> sems = new ArrayList<>();
        sems.add(semToAdd);

        courseToAdd.setSemesters(sems);
        courseRepository.save(courseToAdd);
        return true;
    }
}
