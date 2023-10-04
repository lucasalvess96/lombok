package com.study.lombok.person.Dto;

import com.study.lombok.address.AddressEntity;
import com.study.lombok.person.PersonEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record PersonCreateDto(

        Long id,

        @NotBlank(message = "Nome nao deve está vazio") @Size(min = 3, max = 32) @Pattern(regexp = "^([A-Za-z]+[A-Za-z ])*$", message = "nome deve conter apenas letras") String name,

        @NotBlank(message = "idade deve ser preenchida") @Size(min = 2, max = 2) String age,

        @NotBlank(message = "CPF deve ser preenchido") String cpf,

        @NotBlank(message = "email deve ser preenchido") @Email String email,

        @NotBlank(message = "senha deve ser preenchida, deve conter no minímo 6 caracteres") @Length(min = 6, max = 32) String password,

        @Valid AddressEntity addressEntity) {
    public PersonCreateDto(PersonEntity personEntity) {
        this(personEntity.getId(), personEntity.getName(), personEntity.getAge(), personEntity.getCpf(), personEntity.getEmail(), personEntity.getPassword(), personEntity.getAddressEntity());
    }
}
