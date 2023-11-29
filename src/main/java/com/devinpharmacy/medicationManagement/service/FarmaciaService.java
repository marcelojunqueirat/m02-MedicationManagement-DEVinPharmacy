package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository farmaciaRepo;
}
