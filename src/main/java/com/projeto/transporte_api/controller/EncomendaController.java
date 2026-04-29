package com.projeto.transporte_api.controller;

import com.projeto.transporte_api.dto.EncomendaRequestDTO;
import com.projeto.transporte_api.dto.EncomendaResponseDTO;
import com.projeto.transporte_api.dto.EventoRequestDTO;
import com.projeto.transporte_api.dto.EventoResponseDTO;
import com.projeto.transporte_api.service.EncomendaService;
import com.projeto.transporte_api.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encomendas")
@RequiredArgsConstructor
public class EncomendaController {

    private final EncomendaService encomendaService;
    private final EventoService eventoService;

    @PostMapping
    public ResponseEntity<EncomendaResponseDTO> criar(@RequestBody EncomendaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(encomendaService.criar(dto));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<EncomendaResponseDTO> buscar(@PathVariable String codigo) {
        return ResponseEntity.ok(encomendaService.buscarPorCodigo(codigo));
    }

    @PostMapping("/{codigo}/eventos")
    public ResponseEntity<EventoResponseDTO> registrarEvento(@RequestBody EventoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.registrarEvento(dto));
    }

    @GetMapping("/{codigo}/eventos")
    public ResponseEntity<List<EventoResponseDTO>> buscarEventos(@PathVariable String codigo) {
        return ResponseEntity.ok(eventoService.buscarEventos(codigo));
    }
}
