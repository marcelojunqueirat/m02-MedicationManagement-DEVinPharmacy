package com.devinpharmacy.medicationManagement.dto;

import lombok.Data;

@Data
public class EstoqueTransferenciaResponse {
    private Integer nroRegistro;
    private Long cnpjOrigem;
    private Integer quantidadeOrigem;
    private Long cnpjDestino;
    private Integer quantidadeDestino;
}
