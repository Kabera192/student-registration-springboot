package com.auca.studentregistration.service;

import com.auca.studentregistration.dto.AcademicUnitDto;
import com.auca.studentregistration.entity.AcademicUnit;
import com.auca.studentregistration.entity.EAcademicUnit;
import com.auca.studentregistration.repository.AcademicUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcademicUnitService {
    private final AcademicUnitRepository academicUnitRepository;

    public List<AcademicUnit> findByUnitType(String unitType){
        return academicUnitRepository.findByAcademicUnit(EAcademicUnit.valueOf(unitType));
    }

    public boolean saveAcademicUnit(AcademicUnitDto academicUnit){
        Optional<AcademicUnit> unit = academicUnitRepository.findById(academicUnit.getCode());
        if(unit.isEmpty()){
            AcademicUnit aUnit = new AcademicUnit();
            if(academicUnit.getCode() != null)
                aUnit.setCode(academicUnit.getCode());
            if(academicUnit.getName() != null)
                aUnit.setName(academicUnit.getName());
            if(academicUnit.getUnitType().equals("FACULTY"))
                aUnit.setProgram(academicUnitRepository.findByName(academicUnit.getParentName()));
            if(academicUnit.getUnitType().equals("DEPARTMENT"))
                aUnit.setFaculty(academicUnitRepository.findByName(academicUnit.getParentName()));

            aUnit.setAcademicUnit(EAcademicUnit.valueOf(academicUnit.getUnitType()));
            academicUnitRepository.save(aUnit);
            return true;
        }
        return false;
    }

    public List<AcademicUnitDto> findAllUnits(){
        List<AcademicUnitDto> unitDtos = new ArrayList<>();
        for(AcademicUnit unit : academicUnitRepository.findAll()){
            AcademicUnitDto unitDto = new AcademicUnitDto();
            unitDto.setName(unit.getName());
            unitDto.setCode(unit.getCode());
            unitDto.setUnitType(unit.getAcademicUnit().toString());
            if(unit.getAcademicUnit().toString().equals("FACULTY"))
                unitDto.setParentName(unit.getProgram().getName());
            if(unit.getAcademicUnit().toString().equals("DEPARTMENT"))
                unitDto.setParentName(unit.getFaculty().getName());
            unitDtos.add(unitDto);
        }
        return unitDtos;
    }

}
