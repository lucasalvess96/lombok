package com.study.lombok.person.Dto;

import com.study.lombok.person.PersonEntity;

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
