package com.study.lombok.cep;

public record CepDto(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ddd
) {
    public CepDto(cepEntity cep) {
        this(
                cep.getCep(),
                cep.getLogradouro(),
                cep.getComplemento(),
                cep.getBairro(),
                cep.getLocalidade(),
                cep.getUf(),
                cep.getDdd()
        );
    }
}
