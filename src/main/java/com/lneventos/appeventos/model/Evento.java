package com.lneventos.appeventos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Evento")
    private Long codigo;

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @NotEmpty
    @Column(nullable = false)
    @Size(min=2, max=100, message="Tem de ter pelo menos 2 letras")
    private String local;

    @NotEmpty
    @Column(nullable = false)
    @Size(min=2, max=100, message="Tem de ter pelo menos 2 letras")
    private String data;

    @NotEmpty
    @Column(nullable = false)
    @Size(min=2, max=100, message="Tem de ter pelo menos 2 letras")
    private String horario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.ALL})
    private List<Convidado> convidados;





}
