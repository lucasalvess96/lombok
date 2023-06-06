package com.study.lombok.student.Dto;

import com.study.lombok.student.StudentEntity;

public record StudentListDto(String name, String departament) {

    public StudentListDto(StudentEntity student) {
        this(
                student.getName(),
                student.getDepartament()
        );
    }
}
