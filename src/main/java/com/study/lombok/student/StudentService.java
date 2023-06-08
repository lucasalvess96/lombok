package com.study.lombok.student;

import com.study.lombok.student.Dto.StudentCreateDto;
import com.study.lombok.student.Dto.StudentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public record StudentService(StudentRepository studentRepository) {

    public StudentCreateDto studentCreate(StudentCreateDto studentCreateDto) {
        StudentEntity student = new StudentEntity();
        student.setName(studentCreateDto.name());
        student.setAge(studentCreateDto.age());
        student.setDepartament(studentCreateDto.departament());
        student.setCourseEntitySet(studentCreateDto.courseEntitySet());
        return new StudentCreateDto(studentRepository.save(student));
    }

    public Page<StudentListDto> studentList(Pageable pageable) {
        Page<StudentEntity> studentEntityPagE = studentRepository.findAll(pageable);
        return studentEntityPagE.map(StudentListDto::new);
    }
}
