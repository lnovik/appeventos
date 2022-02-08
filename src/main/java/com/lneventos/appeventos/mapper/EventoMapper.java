package com.lneventos.appeventos.mapper;

import com.lneventos.appeventos.dto.ConvidadoDTO;
import com.lneventos.appeventos.dto.EventoDTO;
import com.lneventos.appeventos.model.Convidado;
import com.lneventos.appeventos.model.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventoMapper {

    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);


    Evento toModel(EventoDTO eventoDTO);

    EventoDTO toDTO(Evento evento);

    Convidado toModel(ConvidadoDTO convidadoDTO);

    ConvidadoDTO toDTO(Convidado convidado);

}
