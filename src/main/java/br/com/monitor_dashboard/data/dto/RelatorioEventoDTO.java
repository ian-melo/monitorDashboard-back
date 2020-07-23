package br.com.monitor_dashboard.data.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonPropertyOrder(value = { "id", "equipamento", "alarme", "data" })
public class RelatorioEventoDTO extends RepresentationModel<RelatorioEventoDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String equipamento;
	private String alarme;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate data;
}
