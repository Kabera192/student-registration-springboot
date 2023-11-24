package com.auca.studentregistration.repository;

import com.auca.studentregistration.entity.Semester;
import com.auca.studentregistration.entity.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, String> {
    List<StudentRegistration> findBySemester(Semester semester);
}
