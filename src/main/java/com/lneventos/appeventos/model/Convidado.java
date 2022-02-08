package com.lneventos.appeventos.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Convidado")
    private Long codigo;

    @NotEmpty
    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    @Size(min=2, max=100, message="Tem de ter pelo menos 2 letras")
    private String nomeConvidado;

    @ManyToOne
    private Evento evento;


}