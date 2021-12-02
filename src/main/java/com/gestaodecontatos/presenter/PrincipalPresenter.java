package com.gestaodecontatos.presenter;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.packer.Empacotador;
import com.gestaodecontatos.view.PrincipalView;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 *
 * @author Daniel Hand Santiago - 2018200011
 */
public class PrincipalPresenter {

    public static void main(String[] args) {
        PrincipalView view = new PrincipalView();
        ContatoCollection contatos = null;

        File arq = new File("contatos.dat");

        try {
            if (arq.exists()) {
                contatos = new ContatoCollection(Empacotador.deserializar(arq));
            } else {
                contatos = new ContatoCollection();
            }

        } catch (Exception ex) {
            System.err.println("Erro ao deserializar");
        }

        botoes(view, contatos, arq);

    }

    private static void botoes(PrincipalView view, ContatoCollection contatos, File arq) {
        view.getBtnNovoContato().addActionListener((ActionEvent ae) -> {
            adicionarContato(contatos);
        });

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar(view, arq, contatos);
        });

        view.getBtnListarContatos().addActionListener((ActionEvent ae) -> {
            listarContatos(contatos);
        });
    }

    private static void adicionarContato(ContatoCollection contatos) {
        new IncluirContatoPresenter(contatos);
    }

    private static void fechar(PrincipalView view, File arq, ContatoCollection contatos) {
        try {
            Empacotador.serializar(arq, contatos);
        } catch (Exception ex) {
            System.err.println("Erro ao serializar!");
        }
        view.dispose();
    }

    private static void listarContatos(ContatoCollection contatos) {
        new ListarContatosPresenter(contatos);
    }
}
