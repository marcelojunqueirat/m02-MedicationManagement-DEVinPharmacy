package com.devinpharmacy.medicationManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEDICAMENTOS")
public class Medicamento {
    @Id
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;

    @Enumerated(EnumType.STRING)
    private TipoMedicamento tipo;
}
