package com.study.lombok.Person;

import com.study.lombok.Person.Dto.PersonCreateDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PersonCreateDto> create(@RequestBody @Valid PersonCreateDto personCreateDto){
        PersonCreateDto createDto = personService.createPerson(personCreateDto);
        if (createDto != null) return new ResponseEntity<>(createDto, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
