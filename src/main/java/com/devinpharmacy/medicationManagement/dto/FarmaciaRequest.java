package com.devinpharmacy.medicationManagement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FarmaciaRequest {
    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Long cnpj;

    @NotEmpty(message = "{required.field}")
    private String razaoSocial;

    @NotEmpty(message = "{required.field}")
    private String nomeFantasia;

    @NotEmpty(message = "{required.field}")
    private String email;

    private String telefone;

    @NotEmpty(message = "{required.field}")
    private String celular;

    @Valid
    private EnderecoRequest endereco;
}
