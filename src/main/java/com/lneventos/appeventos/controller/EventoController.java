package com.lneventos.appeventos.controller;


import com.lneventos.appeventos.model.Convidado;
import com.lneventos.appeventos.model.Evento;
import com.lneventos.appeventos.repository.ConvidadoRepository;
import com.lneventos.appeventos.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventoController {


    private EventoRepository eventoRepository;

    private ConvidadoRepository convidadoRepository;


    @GetMapping("/cadastrarEvento")
    public String form() {
        return "evento/formEvento";
    }

    @PostMapping("/cadastrarEvento")
    public String form(@Valid @ModelAttribute Evento evento, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadastrarEvento";
        }
        eventoRepository.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso");
        return "redirect:/cadastrarEvento";
    }

    @GetMapping("/eventos")
    public ModelAndView listaEventos() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("eventos", eventoRepository.findAll());
        return modelAndView;

    }

    @GetMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
         Evento evento = eventoRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("evento/detalhesEvento");
        modelAndView.addObject("evento", evento);
        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
        modelAndView.addObject("convidados", convidados);

        return modelAndView;
    }

    @GetMapping("/deletarEvento")
    public String deletarEvento(long codigo, RedirectAttributes attributes) {
        Evento evento = eventoRepository.findByCodigo(codigo);
        eventoRepository.delete(evento);
        attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
        return "redirect:/eventos";
    }
    @GetMapping("/deletarConvidado")
    public String deletarConvidado(long codigo){
        Convidado convidado = convidadoRepository.findById(codigo);
        convidadoRepository.delete(convidado);
        Evento evento = convidado.getEvento();
        long codigoLong = evento.getCodigo();
        String codigoEvento = "" + codigoLong;
        return "redirect:/" + codigoEvento;
    }

    @PostMapping("/{codigo}")
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid @ModelAttribute Convidado convidado, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{codigo}";
        }
        Evento evento = eventoRepository.findByCodigo(codigo);
        convidado.setEvento(evento);
        convidadoRepository.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado Adicionado com Sucesso!");
        return "redirect:/{codigo}";
    }


}
