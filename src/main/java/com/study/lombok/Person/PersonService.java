package com.study.lombok.Person;

import com.study.lombok.Address.AddressEntity;
import com.study.lombok.Address.AddressRepository;
import com.study.lombok.Person.Dto.PersonCreateDto;
import com.study.lombok.Person.Dto.PersonDetailDto;
import com.study.lombok.Person.Dto.PersonListDto;
import com.study.lombok.configuration.ErrorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Page<PersonListDto> listPerson(Pageable pageable) {
        Page<PersonEntity> personEntityPage = personRepository.findAll(pageable);
        return personEntityPage.map(PersonListDto::new);
    }

    public Optional<PersonDetailDto> detailPerson(Long id) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        return personEntity.map(entity -> Optional.of(new PersonDetailDto(entity)))
                .orElseThrow(() -> new ErrorRequest("Usuário não encontrado"));
    }
}
