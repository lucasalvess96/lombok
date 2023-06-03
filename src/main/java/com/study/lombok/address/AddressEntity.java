package com.study.lombok.address;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.study.lombok.person.PersonEntity;
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
public class AddressEntity {

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private Long number;

    private String city;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "addressEntity", fetch = FetchType.EAGER)
    @JsonManagedReference
    private PersonEntity personEntity;
}
