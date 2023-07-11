package com.study.lombok.contact;

public record ContactDto(Long id, String email, String phone) {
    public ContactDto(ContactEntity contactEntity) {
        this(
        contactEntity.getId(),
        contactEntity.getEmail(),
        contactEntity.getPhone()
        );
    }
}
