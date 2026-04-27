package com.projeto.transporte_api.dto;

import com.projeto.transporte_api.entity.StatusEncomenda;

import java.time.OffsetDateTime;

public record EncomendaResponseDTO(Long id, String codigoDeRastreio,OffsetDateTime dataEntrega, OffsetDateTime dataCriacao, StatusEncomenda statusEncomenda, String destinatario, String remetente) {
}
