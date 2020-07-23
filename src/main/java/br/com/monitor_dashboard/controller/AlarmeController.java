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

import br.com.monitor_dashboard.data.dto.RelatorioAlarmeDTO;
import br.com.monitor_dashboard.service.AlarmeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoints - Relatório sumazirado de alarmes")
@RestController
@RequestMapping("/api/alarmes/v1")
public class AlarmeController {

    private final AlarmeService service;
	private final PagedResourcesAssembler<RelatorioAlarmeDTO> assembler;

	@Autowired
    public AlarmeController(AlarmeService service, PagedResourcesAssembler<RelatorioAlarmeDTO> assembler) {
		this.service = service;
		this.assembler = assembler;
	}

    @GetMapping(value = "/{id}",produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "Obtém o relatório sumazirado do alarme dado ID")
    public RelatorioAlarmeDTO findById(@PathVariable("id") Long id) {
    	RelatorioAlarmeDTO dto = service.findById(id);
        dto.add(linkTo(methodOn(AlarmeController.class).findById(id)).withSelfRel());
        return dto;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "Obtém o relatório sumazirado de todos os alarmes")
    public ResponseEntity<PagedModel<EntityModel<RelatorioAlarmeDTO>>> findAll(
    		@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "0") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

    	Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = (limit <= 0)? Pageable.unpaged() : PageRequest.of(page, limit, Sort.by(sortDirection, "id"));

        Page<RelatorioAlarmeDTO> relatorio = service.findAll(pageable);
        relatorio.stream().forEach(p ->
        		p.add(linkTo(methodOn(AlarmeController.class).findById(p.getId())).withSelfRel()));

        return new ResponseEntity<>(assembler.toModel(relatorio), HttpStatus.OK);
    }

    @GetMapping(value = "/{inicio}/{fim}", produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "Obtém o relatório sumazirado dos alarmes entre datas")
    public ResponseEntity<PagedModel<EntityModel<RelatorioAlarmeDTO>>> findPersonByName(
    		@PathVariable("inicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate inicio,
    		@PathVariable("fim") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fim,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "0") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

    	Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = (limit <= 0)? Pageable.unpaged() : PageRequest.of(page, limit, Sort.by(sortDirection, "id"));

        Page<RelatorioAlarmeDTO> relatorio = service.findBetweenDatas(inicio, fim, pageable);
        relatorio.stream().forEach(r ->
        		r.add(linkTo(methodOn(AlarmeController.class).findById(r.getId())).withSelfRel()));

        return new ResponseEntity<>(assembler.toModel(relatorio), HttpStatus.OK);
    }
}
