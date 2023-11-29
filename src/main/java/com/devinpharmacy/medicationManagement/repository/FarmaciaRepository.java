package com.devinpharmacy.medicationManagement.repository;

import com.devinpharmacy.medicationManagement.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
}
