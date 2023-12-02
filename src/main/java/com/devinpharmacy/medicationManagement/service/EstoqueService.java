package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.exception.EstoqueNegativoException;
import com.devinpharmacy.medicationManagement.exception.RegistroNaoEncontradoException;
import com.devinpharmacy.medicationManagement.model.Estoque;
import com.devinpharmacy.medicationManagement.model.IdEstoque;
import com.devinpharmacy.medicationManagement.repository.EstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepo;

    @Transactional
    public List<Estoque> consultar() {
        return estoqueRepo.findAll();
    }

    @Transactional
    public List<Estoque> consultar(Long cnpj) {
        return estoqueRepo.findByCnpj(cnpj);
    }

    @Transactional
    public Estoque salvar(Estoque estoque) {
        boolean existe = estoqueRepo.existsById(new IdEstoque(estoque.getCnpj(), estoque.getNroRegistro()));
        estoque = estoqueRepo.save(estoque);
        return estoque;
    }

    @Transactional
    public Estoque salvarEntrada(Estoque estoque) {
        estoque.setDataAtualizacao(LocalDateTime.now());

        Optional<Estoque> estoqueAtual = estoqueRepo.findById(new IdEstoque(estoque.getCnpj(), estoque.getNroRegistro()));
        if (estoqueAtual.isEmpty()){
            return estoqueRepo.save(estoque);
        }

        Integer quantidadeAtual = estoqueAtual.get().getQuantidade();
        estoque.setQuantidade(quantidadeAtual + estoque.getQuantidade());

        return estoqueRepo.save(estoque);
    }

    @Transactional
    public Estoque salvarSaida(Estoque estoque) {
        Optional<Estoque> estoqueAtual = estoqueRepo.findById(new IdEstoque(estoque.getCnpj(), estoque.getNroRegistro()));

        if(estoqueAtual.isEmpty()){
            throw new RegistroNaoEncontradoException("Estoque", String.format("CNPJ: %s, Número do Registro: %s", estoque.getCnpj(), estoque.getNroRegistro()));
        }

        Integer quantidadeAtual = estoqueAtual.get().getQuantidade();

        if(quantidadeAtual < estoque.getQuantidade()){
            throw new EstoqueNegativoException(estoque.getQuantidade(), quantidadeAtual);
        }

        estoque.setDataAtualizacao(LocalDateTime.now());
        estoque.setQuantidade(quantidadeAtual - estoque.getQuantidade());
        estoque = estoqueRepo.save(estoque);

        if(estoque.getQuantidade() == 0){
            estoqueRepo.delete(estoque);
        }

        return estoque;
    }

}
