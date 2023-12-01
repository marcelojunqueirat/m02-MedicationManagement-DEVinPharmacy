package com.devinpharmacy.medicationManagement.controller;

import com.devinpharmacy.medicationManagement.dto.MedicamentoResponse;
import com.devinpharmacy.medicationManagement.model.Medicamento;
import com.devinpharmacy.medicationManagement.service.MedicamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> consultar() {
        var medicamentos = medicamentoService.consultar();
        var resp = new ArrayList<MedicamentoResponse>();
        for (Medicamento medicamento : medicamentos) {
            var medicamentoDTO = modelMapper.map(medicamento, MedicamentoResponse.class);
            resp.add(medicamentoDTO);
        }
        return ResponseEntity.ok(resp);
    }
}
