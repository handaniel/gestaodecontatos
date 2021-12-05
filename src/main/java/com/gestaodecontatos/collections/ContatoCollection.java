package com.gestaodecontatos.collections;

import com.gestaodecontatos.model.Contato;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Daniel Hand Santiago 201820001
 */
public class ContatoCollection implements Serializable {

    private ArrayList<Contato> contatos;

    public ContatoCollection() {
        contatos = new ArrayList<>();
    }

    public ContatoCollection(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public void add(Contato contato) {
        if (contatos.contains(contato)) {
            throw new RuntimeException("Contato já existe!");
        }
        if (contato != null) {
            contatos.add(contato);
            ordena();
        } else {
            throw new RuntimeException("Forneça uma instância válida de um contato!");
        }
    }

    public void update(Contato antigo, Contato novo) {
        if (novo == null) {
            throw new RuntimeException("Forneça uma instância válida de um contato!");
        }
        for (Contato c : this.contatos) {
            if (c.equals(antigo)) {
                c.setNome(novo.getNome());
                c.setTelefone(novo.getTelefone());
            }
        }
        ordena();

    }

    public Contato find(String nome, String telefone) {
        if (nome != null && telefone != null) {
            for (Contato c : contatos) {
                if (c.getNome().equals(nome) && c.getTelefone().equals(telefone)) {
                    return c;
                }
            }
        }

        return null;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public void ordena() {
        Collections.sort(this.contatos);
    }

}
