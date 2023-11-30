package com.devinpharmacy.medicationManagement.controller;

import com.devinpharmacy.medicationManagement.dto.FarmaciaResponse;
import com.devinpharmacy.medicationManagement.model.Farmacia;
import com.devinpharmacy.medicationManagement.service.FarmaciaService;
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
@RequestMapping("/farmacias")
public class FarmaciaController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> consultar() {
        var farmacias = farmaciaService.consultar();
        var resp = new ArrayList<FarmaciaResponse>();
        for (Farmacia farmacia : farmacias) {
            var farmaciaDTO = modelMapper.map(farmacia, FarmaciaResponse.class);
            resp.add(farmaciaDTO);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaResponse> consultar(@PathVariable("cnpj") Long cnpj){
        Farmacia farmacia = farmaciaService.consultar(cnpj);
        var resp = modelMapper.map(farmacia, FarmaciaResponse.class);
        return ResponseEntity.ok(resp);
    }
}
