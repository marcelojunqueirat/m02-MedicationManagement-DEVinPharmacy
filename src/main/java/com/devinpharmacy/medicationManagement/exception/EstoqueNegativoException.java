package com.devinpharmacy.medicationManagement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EstoqueNegativoException extends RuntimeException {
    private Integer quantidadeVenda;
    private Integer quantidadeEstoque;

    public String getMessage() {
        if (quantidadeVenda == null || quantidadeEstoque == null)
            return null;
        return String.format("Quantidade informada para venda: '%d'. Quantidade em estoque: '%d'.", quantidadeVenda, quantidadeEstoque);
    }
}
