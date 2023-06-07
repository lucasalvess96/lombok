package com.study.lombok.cep;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepDto> findCep(@PathVariable String cep) {
        CepDto findCepDto = cepService.findCep(cep);
        return findCepDto != null ? ResponseEntity.ok().body(findCepDto) : ResponseEntity.notFound().build();
    }
}
