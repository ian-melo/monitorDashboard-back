package br.com.monitor_dashboard.data.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder(value = { "id", "descricao", "quantidade" })
public class RelatorioAlarmeDTO extends RepresentationModel<RelatorioAlarmeDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private Long quantidade;
}
