package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepo;
}
