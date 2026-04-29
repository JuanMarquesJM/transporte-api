package com.projeto.transporte_api.service;

import com.projeto.transporte_api.dto.EventoRequestDTO;
import com.projeto.transporte_api.dto.EventoResponseDTO;
import com.projeto.transporte_api.entity.EncomendaEntity;
import com.projeto.transporte_api.entity.EventoEntity;
import com.projeto.transporte_api.entity.StatusEncomenda;
import com.projeto.transporte_api.infrastructure.EncomendaNotFoundException;
import com.projeto.transporte_api.repository.EncomendaRepository;
import com.projeto.transporte_api.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EncomendaRepository encomendaRepository;

    public EventoResponseDTO registrarEvento(EventoRequestDTO dto) {
        EncomendaEntity encomenda = encomendaRepository.findByCodigoDeRastreio(dto.codigoDeRastreio())
                .orElseThrow(() -> new EncomendaNotFoundException("Encomenda não encontrada: " + dto.codigoDeRastreio()));

        if (encomenda.getStatus() == StatusEncomenda.ENTREGUE) {
            throw new RuntimeException("Encomenda já foi entregue e não pode ser alterada.");
        }



        EventoEntity evento = new EventoEntity();
        evento.setEncomenda(encomenda);
        evento.setCidadeHistorico(dto.cidade());
        evento.setStatusEncomendaHistorico(dto.statusEncomenda());
        evento.setDataEHoraHistorico(OffsetDateTime.now());

        encomenda.setStatus(dto.statusEncomenda());
        if (dto.statusEncomenda() == StatusEncomenda.ENTREGUE) {
                encomenda.setDataEntrega(OffsetDateTime.now());
        }
        
        encomendaRepository.save(encomenda);

        eventoRepository.save(evento);

        return toResponse(evento);
    }

    public List<EventoResponseDTO> buscarEventos(String codigoDeRastreio) {
        EncomendaEntity encomenda = encomendaRepository.findByCodigoDeRastreio(codigoDeRastreio)
                .orElseThrow(() -> new EncomendaNotFoundException("Encomenda não encontrada: " + codigoDeRastreio));

        return encomenda.getEventos().stream()
                .map(this::toResponse)
                .toList();
    }

    private EventoResponseDTO toResponse(EventoEntity e) {
        return new EventoResponseDTO(
                e.getId(),
                e.getStatusEncomendaHistorico(),
                e.getCidadeHistorico(),
                e.getDataEHoraHistorico()
        );
    }
}
