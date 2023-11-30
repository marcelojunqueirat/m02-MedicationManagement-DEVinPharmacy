package com.devinpharmacy.medicationManagement.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EnderecoRequest {
    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Long cep;

    @NotEmpty(message = "{required.field}")
    private String logradouro;

    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Integer numero;

    @NotEmpty(message = "{required.field}")
    private String bairro;

    @NotEmpty(message = "{required.field}")
    private String cidade;

    @NotEmpty(message = "{required.field}")
    private String estado;

    private String complemento;

    @NotNull(message = "{required.field}")
    private Double latitude;

    @NotNull(message = "{required.field}")
    private Double longitude;
}
