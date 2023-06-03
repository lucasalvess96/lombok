package com.study.lombok.address.Dto;

import com.study.lombok.address.AddressEntity;
import com.study.lombok.person.PersonEntity;

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
