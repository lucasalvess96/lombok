package com.study.lombok.Person;

import com.study.lombok.Address.AddressEntity;
import com.study.lombok.Address.AddressRepository;
import com.study.lombok.Person.Dto.PersonCreateDto;
import org.springframework.stereotype.Service;

@Service
public record PersonService(PersonRepository personRepository, AddressRepository addressRepository) {

    public PersonCreateDto createPerson(PersonCreateDto personCreateDto) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personCreateDto.name());
        personEntity.setAge(personCreateDto.age());
        personEntity.setCpf(personCreateDto.cpf());
        personEntity.setEmail(personCreateDto.email());
        personEntity.setPassword(personCreateDto.password());

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(personCreateDto.addressEntity().getStreet());
        addressEntity.setNumber(personCreateDto.addressEntity().getNumber());
        addressEntity.setCity(personCreateDto.addressEntity().getCity());

        personEntity.setAddressEntity(addressRepository.save(addressEntity));
        return new PersonCreateDto(personRepository.save(personEntity));
    }
}
