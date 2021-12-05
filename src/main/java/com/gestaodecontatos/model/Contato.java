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
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isBlank()) {
            this.nome = nome;
        } else {
            throw new RuntimeException("Nome inválido!");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        String padrao = "^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$";
        if (!telefone.isBlank() && telefone.matches(padrao)) {
            this.telefone = telefone;
        } else {
            throw new RuntimeException("Telefone inválido!");
        }
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
