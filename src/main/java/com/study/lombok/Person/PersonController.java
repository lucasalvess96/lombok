package com.study.lombok.Person;

import com.study.lombok.Person.Dto.PersonCreateDto;
import com.study.lombok.Person.Dto.PersonDetailDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
@CrossOrigin
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<PersonCreateDto> create(@RequestBody @Valid PersonCreateDto personCreateDto) {
        PersonCreateDto createDto = personService.createPerson(personCreateDto);
        return ResponseEntity.ok().body(createDto);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<PersonDetailDto> detail(@PathVariable @Valid Long id){
        Optional<PersonDetailDto> personDetailDto = personService.detailPerson(id);
        return personDetailDto.map(detailDto -> ResponseEntity.status(HttpStatus.OK).body(detailDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
