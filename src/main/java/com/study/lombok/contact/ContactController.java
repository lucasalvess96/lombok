package com.study.lombok.contact;

import com.study.lombok.person.PersonEntity;
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
@CrossOrigin
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<ContactDto> create(@RequestBody @Valid ContactDto contactDto) {
        ContactDto createDto = contactService.save(contactDto);
        return new ResponseEntity<>(createDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> list(@PageableDefault(direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(contactService.contactList(pageable));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ContactDto> detail(@PathVariable @Valid Long id) {
        Optional<ContactDto> contactDetailDto = contactService.ContactDetail(id);
        return contactDetailDto.map(detailDto -> ResponseEntity.ok().body(detailDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonEntity> delete(@PathVariable Long id) {
        contactService.contactDelete(id);
        return ResponseEntity.ok().build();
    }

}
