package com.study.lombok.Address.Dto;

import com.study.lombok.Address.AddressEntity;
import com.study.lombok.Person.PersonEntity;

public record AddressCreateDto(
        String street,
        Long number,
        String city,
        PersonEntity personEntity
    ) {
    public AddressCreateDto(AddressEntity addressEntity) {
        this(
                addressEntity.getStreet(),
                addressEntity.getNumber(),
                addressEntity.getCity(),
                addressEntity.getPersonEntity()
        );
    }
}
