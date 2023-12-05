package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.dto.EstoqueTransferenciaRequest;
import com.devinpharmacy.medicationManagement.dto.EstoqueTransferenciaResponse;
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
    public Estoque consultar(Long cnpj, Integer nroRegistro) {
        return estoqueRepo.findById(new IdEstoque(cnpj, nroRegistro))
                .orElseThrow(() -> new RegistroNaoEncontradoException("Estoque", String.format("CNPJ: %s, Número do Registro: %s", cnpj, nroRegistro)));
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
        if (estoqueAtual.isEmpty()) {
            return estoqueRepo.save(estoque);
        }

        Integer quantidadeAtual = estoqueAtual.get().getQuantidade();
        estoque.setQuantidade(quantidadeAtual + estoque.getQuantidade());

        return estoqueRepo.save(estoque);
    }

    @Transactional
    public Estoque salvarSaida(Estoque estoque) {
        Estoque estoqueAtual = consultar(estoque.getCnpj(), estoque.getNroRegistro());
        Integer quantidadeAtual = estoqueAtual.getQuantidade();

        if (quantidadeAtual < estoque.getQuantidade()) {
            throw new EstoqueNegativoException(estoque.getQuantidade(), quantidadeAtual);
        }

        estoque.setDataAtualizacao(LocalDateTime.now());
        estoque.setQuantidade(quantidadeAtual - estoque.getQuantidade());
        estoque = estoqueRepo.save(estoque);

        if (estoque.getQuantidade() == 0) {
            estoqueRepo.delete(estoque);
        }

        return estoque;
    }

    @Transactional
    public EstoqueTransferenciaResponse transferencia(EstoqueTransferenciaRequest estoqueTransferencia) {
        Estoque estoqueOrigem = consultar(estoqueTransferencia.getCnpjOrigem(), estoqueTransferencia.getNroRegistro());
        Estoque estoqueDestino = consultar(estoqueTransferencia.getCnpjDestino(), estoqueTransferencia.getNroRegistro());

        if(estoqueOrigem.getQuantidade() < estoqueTransferencia.getQuantidade()){
            throw new EstoqueNegativoException(estoqueTransferencia.getQuantidade(), estoqueOrigem.getQuantidade());
        }

        Integer quantidadeAposSaidaOrigem = estoqueOrigem.getQuantidade() - estoqueTransferencia.getQuantidade();
        estoqueOrigem.setQuantidade(quantidadeAposSaidaOrigem);
        estoqueOrigem.setDataAtualizacao(LocalDateTime.now());

        estoqueDestino.setQuantidade(estoqueDestino.getQuantidade() + estoqueTransferencia.getQuantidade());
        estoqueDestino.setDataAtualizacao(LocalDateTime.now());

        estoqueOrigem = estoqueRepo.save(estoqueOrigem);
        estoqueDestino = estoqueRepo.save(estoqueDestino);

        if (estoqueOrigem.getQuantidade() == 0){
            estoqueRepo.delete(estoqueOrigem);
        }

        var estoqueTransferenciaResponse = new EstoqueTransferenciaResponse();
        estoqueTransferenciaResponse.setNroRegistro(estoqueOrigem.getNroRegistro());
        estoqueTransferenciaResponse.setCnpjOrigem(estoqueOrigem.getCnpj());
        estoqueTransferenciaResponse.setQuantidadeOrigem(estoqueOrigem.getQuantidade());
        estoqueTransferenciaResponse.setCnpjDestino(estoqueDestino.getCnpj());
        estoqueTransferenciaResponse.setQuantidadeDestino(estoqueDestino.getQuantidade());

        return estoqueTransferenciaResponse;
    }
}
