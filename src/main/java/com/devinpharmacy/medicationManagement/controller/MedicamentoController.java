package com.devinpharmacy.medicationManagement.controller;

import com.devinpharmacy.medicationManagement.dto.MedicamentoRequest;
import com.devinpharmacy.medicationManagement.dto.MedicamentoResponse;
import com.devinpharmacy.medicationManagement.model.Medicamento;
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

    @PostMapping
    public ResponseEntity<MedicamentoResponse> cadastrar(@RequestBody @Valid MedicamentoRequest request) {
        var medicamento = modelMapper.map(request, Medicamento.class);
        medicamento = medicamentoService.salvar(medicamento);
        var resp = modelMapper.map(medicamento, MedicamentoResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
