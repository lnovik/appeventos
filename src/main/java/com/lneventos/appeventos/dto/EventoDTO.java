package com.lneventos.appeventos.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private Long codigo;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String local;

    @NotEmpty
    private String data;

    @NotEmpty
    private String horario;

    @NotEmpty
    @Valid
    private List<ConvidadoDTO> convidados;




}
