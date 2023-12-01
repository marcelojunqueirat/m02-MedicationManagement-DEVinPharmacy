package com.devinpharmacy.medicationManagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueAtualizadoResponse {
    private Long cnpj;
    private Integer nroRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
