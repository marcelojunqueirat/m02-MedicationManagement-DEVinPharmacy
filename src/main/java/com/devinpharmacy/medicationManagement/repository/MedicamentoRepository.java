package com.devinpharmacy.medicationManagement.repository;

import com.devinpharmacy.medicationManagement.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
}
