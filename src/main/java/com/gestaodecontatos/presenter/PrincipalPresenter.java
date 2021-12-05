package com.gestaodecontatos.presenter;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.packer.Empacotador;
import com.gestaodecontatos.view.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        init(view, contatos, arq);

    }

    private static void init(PrincipalView view, ContatoCollection contatos, File arq) {

        view.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    Empacotador.serializar(arq, contatos);
                } catch (Exception ex) {
                    System.err.println("Erro ao serializar!");
                }
            }
        });

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
        new IncluirContatoPresenter(contatos, "Incluir contato");
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
