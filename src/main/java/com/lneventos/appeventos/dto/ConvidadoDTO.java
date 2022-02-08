package com.lneventos.appeventos.dto;

import com.lneventos.appeventos.model.Evento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConvidadoDTO {

    private Long codigo;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    private String nomeConvidado;

    @NotEmpty
    @Valid
    private Evento evento;

}
