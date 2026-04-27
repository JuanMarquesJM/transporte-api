package com.projeto.transporte_api.dto;

import com.projeto.transporte_api.entity.StatusEncomenda;

public record EventoRequestDTO(String codigoDeRastreio, String cidade, StatusEncomenda statusEncomenda) {
}
