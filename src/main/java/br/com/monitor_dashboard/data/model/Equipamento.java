package br.com.monitor_dashboard.data.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name ="itmn_equipamento")
public class Equipamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_equip")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hostname", nullable = false)
    private String hostname;
    @Column(name = "ipaddr", nullable = false)
    private String address;

    @OneToMany(mappedBy = "equipamento")
    private List<Evento> eventos;
}
