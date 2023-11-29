package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.exception.RegistroJaExistenteException;
import com.devinpharmacy.medicationManagement.model.Farmacia;
import com.devinpharmacy.medicationManagement.repository.FarmaciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository farmaciaRepo;

    @Transactional
    public List<Farmacia> consultar() {
        return farmaciaRepo.findAll();
    }

    @Transactional
    public Farmacia salvar(Farmacia farmacia) {
        boolean existe = farmaciaRepo.existsById(farmacia.getCnpj());
        if (existe) {
            throw new RegistroJaExistenteException("Farmácia", farmacia.getCnpj());
        }
        farmacia = farmaciaRepo.save(farmacia);
        return farmacia;
    }
}
