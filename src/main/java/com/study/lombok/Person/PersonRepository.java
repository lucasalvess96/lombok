package com.study.lombok.Person;

import com.study.lombok.Person.Dto.PersonSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Page<PersonSearchDto> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
