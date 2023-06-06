package com.study.lombok.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.lombok.student.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String abbreviation;

    private int modules;

    private double fee;

    @ManyToMany(mappedBy = "courseEntitySet", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<StudentEntity> studentEntities = new HashSet<>();;
}
