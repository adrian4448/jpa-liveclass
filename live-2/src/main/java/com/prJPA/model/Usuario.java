package com.prJPA.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    private Long id;

    private String login;

    private String senha;

    private String nome;

    private LocalDateTime ultimoAcesso;

    @ManyToOne
    private Dominio dominio;

    @OneToOne(mappedBy = "usuario")
    private Configuracao configuracao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(nome, usuario.nome) && Objects.equals(ultimoAcesso, usuario.ultimoAcesso) && Objects.equals(configuracao, usuario.configuracao) && Objects.equals(dominio, usuario.dominio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha, nome, ultimoAcesso, configuracao, dominio);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(LocalDateTime ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }
}
