package com.auca.studentregistration.entity;

public enum EAcademicUnit {

    PROGRAM("Program"),
    FACULTY("Faculty"),
    DEPARTMENT("Department"),
    NOT_APPLICABLE("Not Applicable");

    private final String label;

    EAcademicUnit(String label){
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
