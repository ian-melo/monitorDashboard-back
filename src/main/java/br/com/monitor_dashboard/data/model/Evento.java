package br.com.monitor_dashboard.data.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name ="itmn_evento")
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "num_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_evt")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_alarme")
    private Alarme alarme;
    @ManyToOne
    @JoinColumn(name = "id_equip")
    private Equipamento equipamento;
}
