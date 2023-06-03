package com.study.lombok.person;

import com.study.lombok.person.Dto.PersonCreateDto;
import com.study.lombok.person.Dto.PersonDetailDto;
import com.study.lombok.person.Dto.PersonListDto;
import com.study.lombok.person.Dto.PersonSearchDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<PersonListDto>> list(@PageableDefault(direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.listPerson(pageable));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<PersonDetailDto> detail(@PathVariable @Valid Long id) {
        Optional<PersonDetailDto> personDetailDto = personService.detailPerson(id);
        return personDetailDto.map(detailDto -> ResponseEntity.status(HttpStatus.OK)
                .body(detailDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<PersonCreateDto> update(@PathVariable Long id, @RequestBody @Valid PersonCreateDto personCreateDto) {
        PersonCreateDto personUpdate = personService.updatePerson(id, personCreateDto);
        return new ResponseEntity<>(personUpdate, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PersonSearchDto>> search(@RequestParam String name, Pageable pageable) {
        Page<PersonSearchDto> personSearchDtos = personService.searchPerson(name, pageable);
        if(personSearchDtos != null) {
            return new ResponseEntity<>(personSearchDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonEntity> delete(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
