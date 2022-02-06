package com.lneventos.appeventos.repository;

import com.lneventos.appeventos.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, Long> {

    Evento findByCodigo(long codigo);

}
