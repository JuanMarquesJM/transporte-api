package com.projeto.transporte_api.service;

import com.projeto.transporte_api.dto.EncomendaRequestDTO;
import com.projeto.transporte_api.dto.EncomendaResponseDTO;
import com.projeto.transporte_api.entity.EncomendaEntity;
import com.projeto.transporte_api.entity.StatusEncomenda;
import com.projeto.transporte_api.infrastructure.EncomendaNotFoundException;
import com.projeto.transporte_api.repository.EncomendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EncomendaService {

    private final EncomendaRepository encomendaRepository;

    public EncomendaResponseDTO criar(EncomendaRequestDTO dto) {
        EncomendaEntity encomenda = new EncomendaEntity();
        encomenda.setRemetente(dto.remetente());
        encomenda.setDestinatario(dto.destinatario());
        encomenda.setStatus(StatusEncomenda.COLETADO);
        encomenda.setCodigoDeRastreio(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        encomenda.setDataDeCriacao(OffsetDateTime.now());
        encomendaRepository.save(encomenda);
        return toResponse(encomenda);
    }

    public EncomendaResponseDTO buscarPorCodigo(String codigo) {
        EncomendaEntity encomenda = encomendaRepository.findByCodigoDeRastreio(codigo)
                .orElseThrow(() -> new EncomendaNotFoundException("Encomenda não encontrada: " + codigo));
        return toResponse(encomenda);
    }

    private EncomendaResponseDTO toResponse(EncomendaEntity e) {
        return new EncomendaResponseDTO(
                e.getId(),
                e.getCodigoDeRastreio(),
                e.getDataEntrega(),
                e.getDataDeCriacao(),
                e.getStatus(),
                e.getDestinatario(),
                e.getRemetente()
        );
    }
}
