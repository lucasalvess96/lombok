package com.study.lombok.student.Dto;

import com.study.lombok.course.CourseEntity;
import com.study.lombok.course.Dto.CourseCreateDto;
import com.study.lombok.student.StudentEntity;

import java.util.Set;
import java.util.stream.Collectors;

public record StudentCreateDto(Long id, String name, String age, String departament,
                               Set<CourseCreateDto> courseCreateDtos) {
    public StudentCreateDto(StudentEntity student) {
        this(student.getId(), student.getName(), student.getAge(), student.getDepartament(), convertToCourseCreateDto(student.getCourseEntitySet()));
    }

    private static Set<CourseCreateDto> convertToCourseCreateDto(Set<CourseEntity> courseEntities) {
        return courseEntities.stream().map(courseEntity -> new CourseCreateDto(courseEntity.getId(), courseEntity.getTitle(), courseEntity.getAbbreviation(), courseEntity.getModules(), courseEntity.getFee())).collect(Collectors.toSet());
    }
}
