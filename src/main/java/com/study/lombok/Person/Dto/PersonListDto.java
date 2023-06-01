package com.study.lombok.Person.Dto;

import com.study.lombok.Person.PersonEntity;

public record PersonListDto(
        String name,
        String age,
        String email
) {
    public PersonListDto(PersonEntity personEntity) {
        this(
                personEntity.getName(),
                personEntity.getAge(),
                personEntity.getEmail()
        );
    }
}
