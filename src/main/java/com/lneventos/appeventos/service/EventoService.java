package com.lneventos.appeventos.service;

import com.lneventos.appeventos.dto.ConvidadoDTO;
import com.lneventos.appeventos.dto.EventoDTO;
import com.lneventos.appeventos.mapper.EventoMapper;
import com.lneventos.appeventos.model.Convidado;
import com.lneventos.appeventos.model.Evento;
import com.lneventos.appeventos.repository.ConvidadoRepository;
import com.lneventos.appeventos.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventoService {

    private EventoRepository eventoRepository;

    private final EventoMapper eventoMapper = EventoMapper.INSTANCE;

    private ConvidadoRepository convidadoRepository;

    public String formService() {
        return "evento/formEvento";
    }


    public String formEventoService(@Valid @ModelAttribute EventoDTO eventoDTO, BindingResult result, RedirectAttributes attributes ){
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadastrarEvento";
        }
        Evento eventoTosave = eventoMapper.toModel(eventoDTO);
        eventoRepository.save(eventoTosave);
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso");
        return "redirect:/cadastrarEvento";
    }

    public ModelAndView listaEventosService(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("eventos", eventoRepository.findAll());
        return modelAndView;
    }

    public ModelAndView detalhesEventosService(@PathVariable("codigo") long codigo){
        Evento evento = eventoRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("evento/detalhesEvento");
        modelAndView.addObject("evento", evento);
        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
        modelAndView.addObject("convidados", convidados);

        return modelAndView;
    }

    public String deletarEventoService(long codigo, RedirectAttributes attributes){
        Evento evento = eventoRepository.findByCodigo(codigo);
        eventoRepository.delete(evento);
        attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
        return "redirect:/eventos";
    }

    public String deletarConvidadoService(long codigo){
        Convidado convidado = convidadoRepository.findById(codigo);
        convidadoRepository.delete(convidado);
        Evento evento = convidado.getEvento();
        long codigoLong = evento.getCodigo();
        String codigoEvento = "" + codigoLong;
        return "redirect:/" + codigoEvento;
    }

    public String detalhesEventoPostService(@PathVariable("codigo") long codigo, @Valid @ModelAttribute ConvidadoDTO convidadoDTO, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{codigo}";
        }
        Evento evento = eventoRepository.findByCodigo(codigo);

        convidadoDTO.setEvento(evento);
        Convidado convidadoToSave = eventoMapper.toModel(convidadoDTO);
        convidadoRepository.save(convidadoToSave);
        attributes.addFlashAttribute("mensagem", "Convidado Adicionado com Sucesso!");
        return "redirect:/{codigo}";
    }

}
