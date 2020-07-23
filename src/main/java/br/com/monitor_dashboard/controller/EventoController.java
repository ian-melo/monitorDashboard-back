package br.com.monitor_dashboard.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.monitor_dashboard.data.dto.RelatorioEventoDTO;
import br.com.monitor_dashboard.service.EventoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoints - Relatório de eventos")
@RestController
@RequestMapping("/api/eventos/v1")
public class EventoController {

    private final EventoService service;
	private final PagedResourcesAssembler<RelatorioEventoDTO> assembler;

	@Autowired
    public EventoController(EventoService service, PagedResourcesAssembler<RelatorioEventoDTO> assembler) {
		this.service = service;
		this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "Obtém evento dado ID")
    public RelatorioEventoDTO findById(@PathVariable("id") Long id) {
    	RelatorioEventoDTO dto = service.findById(id);

    	dto.add(linkTo(methodOn(EventoController.class).findById(id)).withSelfRel());
        return dto;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "Obtém todos os eventos")
    public ResponseEntity<PagedModel<EntityModel<RelatorioEventoDTO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "0") int limit,
            @RequestParam(value = "direction", defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = (limit <= 0)? Pageable.unpaged() : PageRequest.of(page, limit, Sort.by(sortDirection, "data"));

        Page<RelatorioEventoDTO> relatorio = service.findAll(pageable);
        relatorio.stream().forEach(r ->
        		r.add(linkTo(methodOn(EventoController.class).findById(r.getId())).withSelfRel()));

        return new ResponseEntity<>(assembler.toModel(relatorio), HttpStatus.OK);
    }

    @GetMapping(value = "/{inicio}/{fim}", produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "Obtém os eventos entre datas")
    public ResponseEntity<?> findAuthorByName(
    		@PathVariable("inicio") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate inicio,
    		@PathVariable("fim") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fim,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "0") int limit,
            @RequestParam(value = "direction", defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = (limit <= 0)? Pageable.unpaged() : PageRequest.of(page, limit, Sort.by(sortDirection, "data"));

        Page<RelatorioEventoDTO> relatorio = service.findBetweenDatas(inicio, fim, pageable);
        relatorio.stream().forEach(r ->
        		r.add(linkTo(methodOn(EventoController.class).findById(r.getId())).withSelfRel()));

        return new ResponseEntity<>(assembler.toModel(relatorio), HttpStatus.OK);
    }
}
