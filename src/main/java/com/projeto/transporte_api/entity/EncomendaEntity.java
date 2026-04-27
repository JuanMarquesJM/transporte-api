package com.projeto.transporte_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "encomendas")
public class EncomendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusEncomenda status;

    @OneToMany(mappedBy = "encomenda")
    private List<EventoEntity> eventos;

    private String remetente;
    private String destinatario;
    private OffsetDateTime dataDeCriacao;
    private String codigoDeRastreio;
    private OffsetDateTime dataEntrega;
}
