package com.study.lombok.student.Dto;

import com.study.lombok.course.CourseEntity;
import com.study.lombok.student.StudentEntity;

import java.util.Set;

public record StudentCreateDto(
        Long id,
        String name,
        String age,
        String departament,
        Set<CourseEntity> courseEntitySet
    ) {
    public StudentCreateDto (StudentEntity student) {
        this (
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getDepartament(),
                student.getCourseEntitySet()
        );
    }
}
