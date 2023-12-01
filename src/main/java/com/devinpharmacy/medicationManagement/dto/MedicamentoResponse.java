package com.devinpharmacy.medicationManagement.dto;

import lombok.Data;

@Data
public class MedicamentoResponse {
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;
    private String tipo;
}
