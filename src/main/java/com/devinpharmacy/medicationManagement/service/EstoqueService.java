package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.exception.RegistroJaExistenteException;
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

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private MedicamentoService medicamentoService;

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
        farmaciaService.consultar(estoque.getCnpj());
        medicamentoService.consultar(estoque.getNroRegistro());
        estoque.setDataAtualizacao(LocalDateTime.now());

        Optional<Estoque> estoqueAtual = estoqueRepo.findById(new IdEstoque(estoque.getCnpj(), estoque.getNroRegistro()));
        if (estoqueAtual.isEmpty()){
            return estoqueRepo.save(estoque);
        }

        Integer quantidadeAtual = estoqueAtual.get().getQuantidade();
        estoque.setQuantidade(quantidadeAtual + estoque.getQuantidade());

        return estoqueRepo.save(estoque);
    }

}
