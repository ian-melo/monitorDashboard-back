package br.com.monitor_dashboard.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.monitor_dashboard.data.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT ev FROM Evento ev "
    		+ "JOIN ev.alarme a " 
    		+ "JOIN ev.equipamento eq "
    		+ "WHERE ev.data BETWEEN :inicio AND :fim "
    		+ "ORDER BY ev.data DESC, a.nome, eq.hostname")
    Page<Evento> findBetweenDatas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim, Pageable pageable);
}
