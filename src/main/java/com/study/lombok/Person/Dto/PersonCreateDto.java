package com.study.lombok.Person.Dto;

import com.study.lombok.Address.AddressEntity;
import com.study.lombok.Person.PersonEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PersonCreateDto(

        @NotBlank(message = "Nome nao deve está vazio")
        @Size(min = 3, max = 32)
        @Pattern(regexp = "^([A-Za-z]+[A-Za-z ])*$")
        String name,

        @NotBlank(message = "idade deve ser preenchida")
        @Size(min =2, max = 2)
        String age,

        @NotBlank(message = "CPF deve ser preenchido")
        String cpf,

        @NotBlank(message = "email deve ser preenchido")
        @Email
        String email,

        String password,

        @Valid
        AddressEntity addressEntity
    ) {
    public PersonCreateDto(PersonEntity personEntity) {
        this(
                personEntity.getName(),
                personEntity.getAge(),
                personEntity.getCpf(),
                personEntity.getEmail(),
                personEntity.getPassword(),
                personEntity.getAddressEntity()
        );
    }
}
