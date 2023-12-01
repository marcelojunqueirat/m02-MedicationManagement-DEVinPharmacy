package com.devinpharmacy.medicationManagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EstoqueRequest {
    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Long cnpj;

    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Integer nroRegistro;

    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Integer quantidade;
}
