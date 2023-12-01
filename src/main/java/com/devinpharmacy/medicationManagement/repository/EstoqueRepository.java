package com.devinpharmacy.medicationManagement.repository;

import com.devinpharmacy.medicationManagement.model.Estoque;
import com.devinpharmacy.medicationManagement.model.IdEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, IdEstoque> {

    List<Estoque> findByCnpj(Long cnpj);
}
