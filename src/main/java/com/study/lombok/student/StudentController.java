package com.study.lombok.student;

import com.study.lombok.student.Dto.StudentCreateDto;
import com.study.lombok.student.Dto.StudentListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<StudentCreateDto> create(@RequestBody @Valid StudentCreateDto studentCreateDto) {
        StudentCreateDto student = studentService.studentCreate(studentCreateDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<StudentListDto>> list(@PageableDefault(direction = Sort.Direction.DESC)Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.studentList(pageable));
    }
}
