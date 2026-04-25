package com.projeto.transporte_api.repository;

import com.projeto.transporte_api.entity.EncomendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncomendaRepository extends JpaRepository<EncomendaEntity, Long> {
}
