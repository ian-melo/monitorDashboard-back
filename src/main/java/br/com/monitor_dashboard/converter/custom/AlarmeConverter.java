package br.com.monitor_dashboard.converter.custom;

import br.com.monitor_dashboard.data.dto.RelatorioAlarmeDTO;
import br.com.monitor_dashboard.data.model.Alarme;

public class AlarmeConverter {

	public static RelatorioAlarmeDTO translateToRelatorioDTO(Alarme entity) {
		return RelatorioAlarmeDTO.builder()
				.id(entity.getId())
				.descricao(entity.getDescricao())
				.quantidade(entity.getEventos().stream().count())
				.build();
	}
}
