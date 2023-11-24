package com.auca.studentregistration.repository;

import com.auca.studentregistration.entity.AcademicUnit;
import com.auca.studentregistration.entity.EAcademicUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicUnitRepository extends JpaRepository<AcademicUnit, String> {
    List<AcademicUnit> findByAcademicUnit(EAcademicUnit unit);
    AcademicUnit findByName(String name);
}
