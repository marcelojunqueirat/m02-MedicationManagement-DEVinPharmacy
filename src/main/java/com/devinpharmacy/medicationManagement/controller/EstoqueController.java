package com.devinpharmacy.medicationManagement.controller;

import com.devinpharmacy.medicationManagement.dto.EstoqueResponse;
import com.devinpharmacy.medicationManagement.model.Estoque;
import com.devinpharmacy.medicationManagement.service.EstoqueService;
import com.devinpharmacy.medicationManagement.service.MedicamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<EstoqueResponse>> consultar(@PathVariable("cnpj") Long cnpj){
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
}
