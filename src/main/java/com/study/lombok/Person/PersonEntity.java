package com.study.lombok.Person;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.study.lombok.Address.AddressEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String age;

    private String cpf;

    private String email;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity addressEntity;
}
