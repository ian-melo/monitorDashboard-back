package br.com.monitor_dashboard.converter.custom;

import br.com.monitor_dashboard.data.dto.RelatorioEventoDTO;
import br.com.monitor_dashboard.data.model.Evento;

public class EventoConverter {
	
	public static RelatorioEventoDTO translateToRelatorioDTO(Evento entity) {
		return RelatorioEventoDTO.builder()
				.id(entity.getId())
				.equipamento(entity.getEquipamento().getHostname())
				.alarme(entity.getAlarme().getNome())
				.data(entity.getData())
				.build();
	}
}
