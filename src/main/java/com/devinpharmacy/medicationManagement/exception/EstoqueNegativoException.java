package com.devinpharmacy.medicationManagement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EstoqueNegativoException extends RuntimeException {
    private Integer quantidadeVendaTransferencia;
    private Integer quantidadeEstoque;

    public String getMessage() {
        if (quantidadeVendaTransferencia == null || quantidadeEstoque == null)
            return null;
        return String.format("Quantidade informada: '%d'. Quantidade em estoque: '%d'.", quantidadeVendaTransferencia, quantidadeEstoque);
    }
}
