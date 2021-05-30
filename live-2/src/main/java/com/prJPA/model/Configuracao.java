package com.prJPA.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Configuracao {

    @Id
    private Long id;

    private Boolean receberNotificacoes;

    private Boolean encerrarSessaoAutomaticamente;

    @MapsId
    @OneToOne
    private Usuario usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuracao that = (Configuracao) o;
        return Objects.equals(id, that.id) && Objects.equals(receberNotificacoes, that.receberNotificacoes) && Objects.equals(encerrarSessaoAutomaticamente, that.encerrarSessaoAutomaticamente) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, receberNotificacoes, encerrarSessaoAutomaticamente, usuario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReceberNotificacoes() {
        return receberNotificacoes;
    }

    public void setReceberNotificacoes(Boolean receberNotificacoes) {
        this.receberNotificacoes = receberNotificacoes;
    }

    public Boolean getEncerrarSessaoAutomaticamente() {
        return encerrarSessaoAutomaticamente;
    }

    public void setEncerrarSessaoAutomaticamente(Boolean encerrarSessaoAutomaticamente) {
        this.encerrarSessaoAutomaticamente = encerrarSessaoAutomaticamente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
