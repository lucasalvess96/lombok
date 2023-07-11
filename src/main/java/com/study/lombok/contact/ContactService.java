package com.study.lombok.contact;

import com.study.lombok.configuration.ErrorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record ContactService(ContactRepository contactRepository) {

    public ContactDto save(ContactDto contactDto) {
        ContactEntity contact = new ContactEntity();
        contact.setEmail(contactDto.email());
        contact.setPhone(contactDto.phone());
        return new ContactDto(contactRepository.save(contact));
    }

    public Page<ContactDto> contactList(Pageable pageable) {
        Page<ContactEntity> contactPage = contactRepository.findAll(pageable);
        return contactPage.map(ContactDto::new);
    }

    public Optional<ContactDto> ContactDetail(Long id) {
        Optional<ContactEntity> contactEntity = contactRepository.findById(id);
        return contactEntity.map(entity -> Optional.of(new ContactDto(entity)))
                .orElseThrow(() -> new ErrorRequest("Usuário não encontrado"));
    }

    public void contactDelete(Long id) {
        if(contactRepository.existsById(id)){
            contactRepository.deleteById(id);
        }else{
            throw new ErrorRequest("Recurso não encontrado");
        }
    }
}
