package com.projeto.transporte_api.repository;

import com.projeto.transporte_api.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
}
