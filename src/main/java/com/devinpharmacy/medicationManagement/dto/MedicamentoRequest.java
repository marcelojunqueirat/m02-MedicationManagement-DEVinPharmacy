package com.devinpharmacy.medicationManagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MedicamentoRequest {
    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Integer nroRegistro;

    @NotEmpty(message = "{required.field}")
    private String nome;

    @NotEmpty(message = "{required.field}")
    private String laboratorio;

    @NotEmpty(message = "{required.field}")
    private String dosagem;

    private String descricao;

    @NotNull(message = "{required.field}")
    @Positive(message = "{invalid.field}")
    private Float preco;

    @NotEmpty(message = "{required.field}")
    private String tipo;
}
