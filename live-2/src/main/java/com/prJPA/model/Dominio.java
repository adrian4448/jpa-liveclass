package com.prJPA.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Dominio {

    @Id
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "dominio")
    private List<Usuario> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dominio dominio = (Dominio) o;
        return Objects.equals(id, dominio.id) && Objects.equals(nome, dominio.nome) && Objects.equals(usuarios, dominio.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuarios);
    }
}
