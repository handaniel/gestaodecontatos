package com.gestaodecontatos.presenter;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.view.PrincipalView;
import java.awt.event.ActionEvent;

/**
 *
 * @author Daniel Hand Santiago - 2018200011
 */
public class PrincipalPresenter {

    public static void main(String[] args) {
        PrincipalView view = new PrincipalView();
        ContatoCollection contatos = new ContatoCollection();

        view.getBtnNovoContato().addActionListener((ActionEvent ae) -> {
            adicionarContato(contatos);
        });

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar(view);
        });

        view.getBtnListarContatos().addActionListener((ActionEvent ae) -> {
            listarContatos(contatos);
        });

    }

    private static void adicionarContato(ContatoCollection contatos) {
        new IncluirContatosPresenter(contatos);
    }

    private static void fechar(PrincipalView view) {
        view.dispose();
    }

    private static void listarContatos(ContatoCollection contatos) {
        new ListarContatosPresenter(contatos);
    }
}
