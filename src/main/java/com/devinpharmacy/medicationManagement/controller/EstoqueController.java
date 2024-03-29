package com.devinpharmacy.medicationManagement.controller;

import com.devinpharmacy.medicationManagement.dto.*;
import com.devinpharmacy.medicationManagement.model.Estoque;
import com.devinpharmacy.medicationManagement.service.EstoqueService;
import com.devinpharmacy.medicationManagement.service.FarmaciaService;
import com.devinpharmacy.medicationManagement.service.MedicamentoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<EstoqueResponse>> consultar(@PathVariable("cnpj") Long cnpj) {
        var estoques = estoqueService.consultar(cnpj);
        var resp = new ArrayList<EstoqueResponse>();
        for (Estoque estoque : estoques) {
            var estoqueDTO = modelMapper.map(estoque, EstoqueResponse.class);
            var produto = medicamentoService.consultar(estoqueDTO.getNroRegistro());
            estoqueDTO.setNome(produto.getNome());
            resp.add(estoqueDTO);
        }
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<EstoqueAtualizadoResponse> entrada(@RequestBody @Valid EstoqueRequest request) {
        var estoque = modelMapper.map(request, Estoque.class);
        farmaciaService.consultar(estoque.getCnpj());
        medicamentoService.consultar(estoque.getNroRegistro());
        estoque = estoqueService.salvarEntrada(estoque);
        var resp = modelMapper.map(estoque, EstoqueAtualizadoResponse.class);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping
    public ResponseEntity<EstoqueAtualizadoResponse> saida(@RequestBody @Valid EstoqueRequest request) {
        var estoque = modelMapper.map(request, Estoque.class);
        farmaciaService.consultar(estoque.getCnpj());
        medicamentoService.consultar(estoque.getNroRegistro());
        estoque = estoqueService.salvarSaida(estoque);
        var resp = modelMapper.map(estoque, EstoqueAtualizadoResponse.class);
        return ResponseEntity.ok(resp);
    }

    @PutMapping
    public ResponseEntity<EstoqueTransferenciaResponse> transferencia(@RequestBody @Valid EstoqueTransferenciaRequest request) {
        farmaciaService.consultar(request.getCnpjOrigem());
        farmaciaService.consultar(request.getCnpjDestino());
        medicamentoService.consultar(request.getNroRegistro());
        var resp = estoqueService.transferencia(request);
        return ResponseEntity.ok(resp);
    }
}
