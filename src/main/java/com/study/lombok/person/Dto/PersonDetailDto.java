package com.study.lombok.person.Dto;

import com.study.lombok.person.PersonEntity;

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
