package com.gestaodecontatos.collections;

import com.gestaodecontatos.model.Contato;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author handaniels
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
        if (find(contato.getNome(), contato.getTelefone()) != null) {
            JOptionPane.showMessageDialog(null, "Contato já existe!");
            throw new RuntimeException("Contato já existe!");
        }
        if (contato != null) {
            contatos.add(contato);
        } else {
            throw new RuntimeException("Forneça uma instância válida de um contato!");
        }
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

}
