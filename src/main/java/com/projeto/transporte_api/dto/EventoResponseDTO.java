package com.projeto.transporte_api.dto;

import com.projeto.transporte_api.entity.StatusEncomenda;

import java.time.OffsetDateTime;

public record EventoResponseDTO(Long id, StatusEncomenda statusEncomenda, String cidadeHistorico, OffsetDateTime dataEHoraHistorico) {
}
