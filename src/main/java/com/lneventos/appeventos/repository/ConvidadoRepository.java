package com.lneventos.appeventos.repository;

import com.lneventos.appeventos.model.Convidado;
import com.lneventos.appeventos.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado,Long> {

    Iterable<Convidado> findByEvento(Evento evento);
}
