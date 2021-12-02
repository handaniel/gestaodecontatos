package com.gestaodecontatos.presenter;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.model.Contato;
import com.gestaodecontatos.view.IncluirContatoView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author handaniels
 */
public class IncluirContatosPresenter {

    private IncluirContatoView view;
    private ContatoCollection contatos;

    public IncluirContatosPresenter(ContatoCollection contatos) {
        this.contatos = contatos;
        view = new IncluirContatoView();

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            salvar();
        });

        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());
    }

    private void fechar() {
        view.dispose();
    }

    private void salvar() {
        String nome = view.getTxtNome().getText();
        String telefone = view.getTxtTelefone().getText();

        Contato contato = new Contato(nome, telefone);

        contatos.add(contato);

        JOptionPane.showMessageDialog(view,
                "Contato de nome " + contato.getNome() + " e de telefone " + contato.getTelefone() + " salvo com sucesso!",
                "Salvo com sucesso!",
                JOptionPane.INFORMATION_MESSAGE);

        fechar();
    }

}
