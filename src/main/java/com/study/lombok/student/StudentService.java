package com.study.lombok.student;

import com.study.lombok.course.CourseEntity;
import com.study.lombok.course.Dto.CourseCreateDto;
import com.study.lombok.student.Dto.StudentCreateDto;
import com.study.lombok.student.Dto.StudentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public record StudentService(StudentRepository studentRepository) {

    public StudentCreateDto studentCreate(StudentCreateDto studentCreateDto) {
        StudentEntity student = new StudentEntity();
        student.setId(studentCreateDto.id());
        student.setName(studentCreateDto.name());
        student.setAge(studentCreateDto.age());
        student.setDepartament(studentCreateDto.departament());

        if (studentCreateDto.courseCreateDtos() != null) {
            Set<CourseEntity> courseEntities = convertToCourseEntitySet(studentCreateDto.courseCreateDtos());
            student.setCourseEntitySet(courseEntities);
        }

        return new StudentCreateDto(studentRepository.save(student));
    }

    public Page<StudentListDto> studentList(Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findAll(pageable);
        return studentEntityPage.map(student -> new StudentListDto(student.getId(), student.getName(), student.getAge(), student.getDepartament(), convertToCourseCreateDto(student.getCourseEntitySet())));
    }

    private static Set<CourseCreateDto> convertToCourseCreateDto(Set<CourseEntity> courseEntities) {
        return courseEntities.stream().map(courseEntity -> new CourseCreateDto(courseEntity.getId(), courseEntity.getTitle(), courseEntity.getAbbreviation(), courseEntity.getModules(), courseEntity.getFee())).collect(Collectors.toSet());
    }

    private Set<CourseEntity> convertToCourseEntitySet(Set<CourseCreateDto> courseCreateDtos) {
        return courseCreateDtos.stream().map(courseCreateDto -> {
            CourseEntity courseEntity = new CourseEntity();
            courseEntity.setId(courseCreateDto.id());
            courseEntity.setTitle(courseCreateDto.title());
            courseEntity.setAbbreviation(courseCreateDto.abbreviation());
            courseEntity.setModules(courseCreateDto.modules());
            courseEntity.setFee(courseCreateDto.fee());
            return courseEntity;
        }).collect(Collectors.toSet());
    }
}
