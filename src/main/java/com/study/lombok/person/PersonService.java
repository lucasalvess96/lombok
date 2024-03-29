package com.study.lombok.person;

import com.study.lombok.address.AddressEntity;
import com.study.lombok.address.AddressRepository;
import com.study.lombok.configuration.ErrorRequest;
import com.study.lombok.person.Dto.PersonCreateDto;
import com.study.lombok.person.Dto.PersonDetailDto;
import com.study.lombok.person.Dto.PersonListDto;
import com.study.lombok.person.Dto.PersonSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record PersonService(PersonRepository personRepository, AddressRepository addressRepository) {

    public PersonCreateDto createPerson(PersonCreateDto personCreateDto) {
        PersonEntity personEntity = new PersonEntity();
        return getPersonCreateDtoAndUpdateDto(personCreateDto, personEntity);
    }

    public Page<PersonListDto> listPerson(Pageable pageable) {
        Page<PersonEntity> personEntityPage = personRepository.findAll(pageable);
        return personEntityPage.map(PersonListDto::new);
    }

    public Optional<PersonDetailDto> detailPerson(Long id) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        return personEntity.map(entity -> Optional.of(new PersonDetailDto(entity))).orElseThrow(() -> new ErrorRequest("Usuário não encontrado"));
    }

    public PersonCreateDto updatePerson(Long id, PersonCreateDto personCreateDto) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(() -> new ErrorRequest("Recurso não encontrado"));
        return getPersonCreateDtoAndUpdateDto(personCreateDto, personEntity);
    }

    public Page<PersonSearchDto> searchPerson(String name, Pageable pageable) {
        Page<PersonEntity> personEntityPage = personRepository.findByNameContainingIgnoreCase(name, pageable);
        return personEntityPage.map(personEntity -> new PersonSearchDto(personEntity.getName()));
    }

    public void deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new ErrorRequest("Recurso não encontrado");
        }
    }

    private PersonCreateDto getPersonCreateDtoAndUpdateDto(PersonCreateDto personCreateDto, PersonEntity personEntity) {
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
