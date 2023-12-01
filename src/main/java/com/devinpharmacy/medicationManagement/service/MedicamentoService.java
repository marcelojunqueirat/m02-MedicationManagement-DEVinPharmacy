package com.devinpharmacy.medicationManagement.service;

import com.devinpharmacy.medicationManagement.exception.RegistroJaExistenteException;
import com.devinpharmacy.medicationManagement.exception.RegistroNaoEncontradoException;
import com.devinpharmacy.medicationManagement.model.Medicamento;
import com.devinpharmacy.medicationManagement.repository.MedicamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepo;

    @Transactional
    public List<Medicamento> consultar() {
        return medicamentoRepo.findAll();
    }

    @Transactional
    public Medicamento consultar(Integer nroRegistro) {
        return medicamentoRepo.findById(nroRegistro)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Medicamento", nroRegistro));
    }

    @Transactional
    public Medicamento salvar(Medicamento medicamento){
        boolean existe = medicamentoRepo.existsById(medicamento.getNroRegistro());
        if(existe){
            throw new RegistroJaExistenteException("Medicamento", medicamento.getNroRegistro());
        }
        medicamento = medicamentoRepo.save(medicamento);
        return medicamento;
    }
}
