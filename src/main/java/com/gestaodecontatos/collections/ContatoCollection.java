package com.gestaodecontatos.collections;

import com.gestaodecontatos.model.Contato;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author handaniels
 */
public class ContatoCollection implements Serializable {

    private ArrayList<Contato> contatos;

    public ContatoCollection() {
        contatos = new ArrayList<>();

    }

    public void add(Contato contato) {
        if (contatos.contains(contato)) {
            throw new RuntimeException("Contato já existe!");
        }
        if (contato != null) {
            contatos.add(contato);
        } else {
            throw new RuntimeException("Forneça uma instância válida de um contato!");
        }
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

}
