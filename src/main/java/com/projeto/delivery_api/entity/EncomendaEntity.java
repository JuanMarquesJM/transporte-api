package com.projeto.delivery_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


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

    private String remetente;
    private String destinatario;
    private LocalDateTime dataDeCriacao;
    private String codigoDeRastreio;
    private LocalDateTime dataEntrega;
}
