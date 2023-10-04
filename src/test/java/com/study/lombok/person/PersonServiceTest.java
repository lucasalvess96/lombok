package com.study.lombok.person;

import com.study.lombok.address.AddressEntity;
import com.study.lombok.address.AddressRepository;
import com.study.lombok.configuration.ErrorRequest;
import com.study.lombok.person.Dto.PersonCreateDto;
import com.study.lombok.person.Dto.PersonDetailDto;
import com.study.lombok.person.Dto.PersonListDto;
import com.study.lombok.person.Dto.PersonSearchDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@SpringJUnitConfig
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    @DisplayName("should create a person successful person")
    void createPerson() {
        AddressEntity expectedAddressEntity = new AddressEntity();
        expectedAddressEntity.setStreet("rua das docas");
        expectedAddressEntity.setNumber(36L);
        expectedAddressEntity.setCity("Belem");

        PersonCreateDto personCreateDto = new PersonCreateDto(1L, "John Doe", "30", "123456789", "john@example.com", "password", expectedAddressEntity);

        when(addressRepository.save(any(AddressEntity.class))).thenAnswer(invocation -> invocation.<AddressEntity>getArgument(0));

        PersonEntity savedPersonEntity = new PersonEntity();
        savedPersonEntity.setId(1L);
        when(personRepository.save(any())).thenReturn(savedPersonEntity);

        PersonCreateDto result = personService.createPerson(personCreateDto);

        verify(personRepository, times(1)).save(any());
        verify(addressRepository, times(1)).save(expectedAddressEntity);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(savedPersonEntity.getId(), result.id());
            assertEquals(personCreateDto.name(), result.name());
            assertEquals(personCreateDto.age(), result.age());
            assertEquals(personCreateDto.cpf(), result.cpf());
            assertEquals(personCreateDto.email(), result.email());
            assertEquals(personCreateDto.password(), result.password());
            assertEquals(expectedAddressEntity.getStreet(), result.addressEntity().getStreet());
            assertEquals(expectedAddressEntity.getNumber(), result.addressEntity().getNumber());
            assertEquals(expectedAddressEntity.getCity(), result.addressEntity().getCity());
        });
    }

    @Test
    @DisplayName("should return person List")
    void listPerson() {
        List<PersonEntity> personEntities = Arrays.asList(new PersonEntity(1L, "John", "30", "123456789", "john@example.com", "password", null), new PersonEntity(2L, "Alice", "25", "987654321", "alice@example.com", "secret", null));

        Page<PersonEntity> page = new PageImpl<>(personEntities);

        when(personRepository.findAll(Pageable.unpaged())).thenReturn(page);

        Page<PersonListDto> result = personService.listPerson(Pageable.unpaged());

        assertAll(() -> {
            assertEquals(personEntities.size(), result.getTotalElements());
            assertEquals(personEntities.get(0).getName(), result.getContent().get(0).name());
            assertEquals(personEntities.get(0).getAge(), result.getContent().get(0).age());
            assertEquals(personEntities.get(0).getEmail(), result.getContent().get(0).email());
            assertEquals(personEntities.get(1).getName(), result.getContent().get(1).name());
            assertEquals(personEntities.get(1).getAge(), result.getContent().get(1).age());
            assertEquals(personEntities.get(1).getEmail(), result.getContent().get(1).email());
        });
    }

    @Test
    @DisplayName("should return person details when person exists")
    void buscaUsuarioValidoDetailPerson() {
        Long id = 1L;
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(id);

        when(personRepository.findById(id)).thenReturn(Optional.of(personEntity));

        Optional<PersonDetailDto> result = personService.detailPerson(id);

        assertAll(() -> {
            assertTrue(result.isPresent());
            assertEquals(id, result.get().id());
        });
    }

    @Test
    @DisplayName("should throw ErrorRequest when person does not exist")
    void BuscaUsuarioInvalidoDetailPerson() {
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ErrorRequest.class, () -> personService.detailPerson(id));
    }

    @Test
    @DisplayName("Should return updatePerson with id")
    void updatePerson() {
    }

    @Test
    @DisplayName("should return a page of PersonSearchDto objects")
    void searchPerson() {
        String name = "John";
        Pageable pageable = Pageable.ofSize(10);
        List<PersonEntity> personEntities = List.of(new PersonEntity(), new PersonEntity());
        Page<PersonEntity> page = new PageImpl<>(personEntities, pageable, personEntities.size());

        when(personRepository.findByNameContainingIgnoreCase(eq(name), eq(pageable))).thenAnswer(invocation -> page);

        Page<PersonSearchDto> result = personService.searchPerson(name, pageable);

        assertNotNull(result);
        assertEquals(personEntities.size(), result.getTotalElements());
        assertEquals(personEntities.get(0).getName(), result.getContent().get(0).name());
        assertEquals(personEntities.get(1).getName(), result.getContent().get(1).name());
    }

    @Test
    @DisplayName("should delete a person")
    void validaDeletePerson() {
        Long idToDelete = 1L;
        when(personRepository.existsById(idToDelete)).thenReturn(true);
        assertDoesNotThrow(() -> personService.deletePerson(idToDelete));
        verify(personRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    @DisplayName("should throw ErrorRequest when trying to delete a non-existent person")
    void deleteNonExistentPerson() {
        Long idToDelete = 1L;
        when(personRepository.existsById(idToDelete)).thenReturn(false);
        assertThrows(ErrorRequest.class, () -> personService.deletePerson(idToDelete));
        verify(personRepository, never()).deleteById(idToDelete);
    }

    @Test
    @DisplayName("Test save and retrieve PersonEntity")
    void personRepository() {
        PersonEntity person = new PersonEntity();
        person.setName("John");
        person.setAge("30");

        personRepository.save(person);

        PersonEntity retrievedPerson = personRepository.findById(person.getId()).orElse(null);

        assertNotNull(retrievedPerson);
        assertEquals(person.getName(), retrievedPerson.getName());
        assertEquals(person.getAge(), retrievedPerson.getAge());
    }

    @Test
    @DisplayName("Test save and retrieve AddressEntity")
    void addressRepository() {
        AddressEntity address = new AddressEntity();
        address.setStreet("123 Main St");
        address.setNumber(123L);
        address.setCity("City");

        addressRepository.save(address);

        AddressEntity retrievedAddress = addressRepository.findById(address.getId()).orElse(null);

        assertNotNull(retrievedAddress);
        assertEquals(address.getStreet(), retrievedAddress.getStreet());
        assertEquals(address.getNumber(), retrievedAddress.getNumber());
        assertEquals(address.getCity(), retrievedAddress.getCity());
    }
}
