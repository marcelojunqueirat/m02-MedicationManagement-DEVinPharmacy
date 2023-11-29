package com.devinpharmacy.medicationManagement.controller;

import com.devinpharmacy.medicationManagement.model.*;
import com.devinpharmacy.medicationManagement.service.EstoqueService;
import com.devinpharmacy.medicationManagement.service.FarmaciaService;
import com.devinpharmacy.medicationManagement.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/inicializacao")
public class InicializacaoController {
    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<?> cargaInicial() {
        var farmarcias = farmaciaService.consultar();
        if (farmarcias.isEmpty()) {
            Endereco endereco1 = new Endereco(88888999L, "Rua Porto Real", 67, "Westeros", "Berlim", "SC", null, 15.23456, 2.8678687);
            Farmacia farmacia1 = new Farmacia(90561736000121L, "DevMed Ltda", "Farmácia DevMed", "devmed@farmacia.com", "(44)4444-4444", "(44)9444-4441", endereco1);
            Endereco endereco2 = new Endereco(8877799L, "Rua Madrid", 76, "Winterfell", "Estocolmo", "SC", null, 19.254356, 3.8987687);
            Farmacia farmacia2 = new Farmacia(43178995000198L, "MedHouse Ltda", "Farmácia MedHouse", "medhouse@farmacia.com", "(55)5555-5555", "(45)95555-5555", endereco2);
            farmaciaService.salvar(farmacia1);
            farmaciaService.salvar(farmacia2);
        }
        return ResponseEntity.ok().build();
    }
}
