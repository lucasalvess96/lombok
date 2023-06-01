package com.study.lombok.Person.Dto;

import com.study.lombok.Person.PersonEntity;

public record PersonDetailDto(
        String mane,
        String age,
        String cpf,
        String email
) {
    public PersonDetailDto(PersonEntity personEntity) {
        this(
                personEntity.getName(),
                personEntity.getAge(),
                personEntity.getCpf(),
                personEntity.getEmail()
        );
    }
}
