package com.study.lombok.course.Dto;

public record CourseCreateDto(Long id, String title, String abbreviation, int modules, double fee) {
}
