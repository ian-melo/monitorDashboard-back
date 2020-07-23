package br.com.monitor_dashboard.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.monitor_dashboard.converter.custom.AlarmeConverter;
import br.com.monitor_dashboard.data.dto.RelatorioAlarmeDTO;
import br.com.monitor_dashboard.data.model.Alarme;
import br.com.monitor_dashboard.exception.ResourceNotFoundException;
import br.com.monitor_dashboard.repository.AlarmeRepository;

@Service
public class AlarmeService {

    private final AlarmeRepository repository;

	@Autowired
    public AlarmeService(AlarmeRepository repository) {
		this.repository = repository;
	}

    public RelatorioAlarmeDTO findById(Long id) {
        Alarme entity = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException(String.format("No alarme found by ID=%s",id)));
        return AlarmeConverter.translateToRelatorioDTO(entity);
    }

    public Page<RelatorioAlarmeDTO> findBetweenDatas(LocalDate inicio, LocalDate fim, Pageable pageable) {
        Page<Alarme> page = repository.findBetweenDatas(inicio, fim, pageable);
        return page.map(AlarmeConverter::translateToRelatorioDTO);
    }

    public Page<RelatorioAlarmeDTO> findAll(Pageable pageable) {
    	Page<Alarme> page = repository.findAll(pageable);
        return page.map(AlarmeConverter::translateToRelatorioDTO);
    }

}
