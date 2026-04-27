package com.projeto.transporte_api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventos")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "encomenda_id")
    private EncomendaEntity encomenda;

    @Enumerated(EnumType.STRING)
    private StatusEncomenda statusEncomendaHistorico;


    private String cidadeHistorico;
    private OffsetDateTime dataEHoraHistorico;


}
