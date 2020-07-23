package br.com.monitor_dashboard.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.monitor_dashboard.data.model.Alarme;

@Repository
public interface AlarmeRepository extends JpaRepository<Alarme, Long> {

    @Query("SELECT DISTINCT a FROM Alarme a "
    		+ "JOIN a.eventos ev "
    		+ "WHERE ev.data BETWEEN :inicio AND :fim "
    		+ "ORDER BY a.id")
    Page<Alarme> findBetweenDatas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim, Pageable pageable);
}
