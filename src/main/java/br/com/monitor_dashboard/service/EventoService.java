package br.com.monitor_dashboard.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.monitor_dashboard.converter.custom.EventoConverter;
import br.com.monitor_dashboard.data.dto.RelatorioEventoDTO;
import br.com.monitor_dashboard.data.model.Evento;
import br.com.monitor_dashboard.exception.ResourceNotFoundException;
import br.com.monitor_dashboard.repository.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository repository;

	@Autowired
    public EventoService(EventoRepository repository) {
		this.repository = repository;
	}

    public RelatorioEventoDTO findById(Long id) {
        Evento entity = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException(String.format("No evento found by ID=%s",id)));
        return EventoConverter.translateToRelatorioDTO(entity);
    }

    public Page<RelatorioEventoDTO> findBetweenDatas(LocalDate inicio, LocalDate fim, Pageable pageable) {
        Page<Evento> page = repository.findBetweenDatas(inicio, fim, pageable);
        return page.map(EventoConverter::translateToRelatorioDTO);
    }

    public Page<RelatorioEventoDTO> findAll(Pageable pageable) {
        Page<Evento> page = repository.findAll(pageable);
        return page.map(EventoConverter::translateToRelatorioDTO);
    }
}
