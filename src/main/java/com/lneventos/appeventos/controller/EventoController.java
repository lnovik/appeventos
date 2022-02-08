package com.lneventos.appeventos.controller;


import com.lneventos.appeventos.dto.ConvidadoDTO;
import com.lneventos.appeventos.dto.EventoDTO;
import com.lneventos.appeventos.repository.ConvidadoRepository;
import com.lneventos.appeventos.service.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventoController {



    private final EventoService eventoService;


    @GetMapping("/cadastrarEvento")
    public String form(){
        return eventoService.formService();
    }

    @PostMapping("/cadastrarEvento")
    public String formularioEvento(EventoDTO eventoDTO, BindingResult result, RedirectAttributes attributes){
        return eventoService.formEventoService(eventoDTO, result, attributes);
    }

    @GetMapping("/eventos")
    public ModelAndView listaEventos() {
        return eventoService.listaEventosService();
    }

    @GetMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
        return eventoService.detalhesEventosService(codigo);
    }

    @GetMapping("/deletarEvento")
    public String deletarEvento(long codigo, RedirectAttributes attributes) {
        return eventoService.deletarEventoService(codigo, attributes);
    }

    @GetMapping("/deletarConvidado")
    public String deletarConvidado(long codigo){
        return eventoService.deletarConvidadoService(codigo);
    }

    @PostMapping("/{codigo}")
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, ConvidadoDTO convidadoDTO, BindingResult result, RedirectAttributes attributes) {
        return eventoService.detalhesEventoPostService(codigo, convidadoDTO, result, attributes);
    }



}
