package br.com.monitor_dashboard.data.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbUsuario")
public class Usuario {

    @Id
    @Column(name = "IdUsuario")
    private Long IdUsuario;

    @Column (name = "NomeUsuario")
    private String NomeUsuario;

    @Column(name = "segredo")
    private String segredo;

    public Usuario(Long IdUsuario,String NomeUsuario, String segredo){
        this.IdUsuario = IdUsuario;
        this.NomeUsuario = NomeUsuario;
        this.segredo = segredo;
    }
    public Usuario(){ }
}
