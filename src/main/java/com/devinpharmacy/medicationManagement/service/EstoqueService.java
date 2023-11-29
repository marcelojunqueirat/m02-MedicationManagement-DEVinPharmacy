package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.exception.RegistroJaExistenteException;
import com.devinpharmacy.medicationManagement.model.Estoque;
import com.devinpharmacy.medicationManagement.model.IdEstoque;
import com.devinpharmacy.medicationManagement.repository.EstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepo;

    @Transactional
    public List<Estoque> consultar() {
        return estoqueRepo.findAll();
    }

    @Transactional
    public Estoque salvar(Estoque estoque) {
        boolean existe = estoqueRepo.existsById(new IdEstoque(estoque.getCnpj(), estoque.getNroRegistro()));
        if (existe) {
            throw new RegistroJaExistenteException("Estoque",
                    String.format("CNPJ: %s, Número do Registro: %s.", estoque.getCnpj(), estoque.getNroRegistro())
            );
        }
        estoque = estoqueRepo.save(estoque);
        return estoque;
    }
}
