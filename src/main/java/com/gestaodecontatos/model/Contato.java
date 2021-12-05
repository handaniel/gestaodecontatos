package com.gestaodecontatos.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author handaniels
 */
public class Contato implements Serializable, Comparable<Contato> {

    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        if (nome != null && telefone != null) {
            this.nome = nome;
            this.telefone = telefone;
        } else {
            throw new RuntimeException("Campo inválido!");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int compareTo(Contato outro) {
        return this.getNome().compareToIgnoreCase(outro.getNome());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        return true;
    }

}
