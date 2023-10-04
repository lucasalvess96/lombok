package com.study.lombok.person.Dto;

import com.study.lombok.person.PersonEntity;

public record PersonDetailDto(

        Long id, String mane, String age, String cpf, String email) {
    public PersonDetailDto(PersonEntity personEntity) {
        this(personEntity.getId(), personEntity.getName(), personEntity.getAge(), personEntity.getCpf(), personEntity.getEmail());
    }
}
