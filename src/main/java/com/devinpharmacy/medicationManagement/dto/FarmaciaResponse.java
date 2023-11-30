package com.devinpharmacy.medicationManagement.dto;

import lombok.Data;

@Data
public class FarmaciaResponse {
    private Long cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;

    private EnderecoResponse endereco;
}
