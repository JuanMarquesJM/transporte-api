package com.projeto.transporte_api.repository;

import com.projeto.transporte_api.entity.EncomendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EncomendaRepository extends JpaRepository<EncomendaEntity, Long> {
    Optional<EncomendaEntity> findByCodigoDeRastreio(String codigo);
}
