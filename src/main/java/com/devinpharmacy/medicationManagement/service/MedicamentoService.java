package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepo;
}
