package com.study.lombok.student.Dto;

import com.study.lombok.course.Dto.CourseCreateDto;

import java.util.Set;

public record StudentListDto(Long id, String name, String age, String departament,
                             Set<CourseCreateDto> courseCreateDtoSet) {
}
