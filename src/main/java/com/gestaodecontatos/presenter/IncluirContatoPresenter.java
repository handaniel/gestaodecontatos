package com.gestaodecontatos.presenter;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.model.Contato;
import com.gestaodecontatos.view.IncluirContatoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Hand Santiago 2018200011
 */
public class IncluirContatoPresenter {

    private IncluirContatoView view;
    private ContatoCollection contatos;

    public IncluirContatoPresenter(ContatoCollection contatos, String titulo) {
        this.contatos = contatos;
        view = new IncluirContatoView();
        view.setTitle(titulo);

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            salvar();
        });

        view.setLocationRelativeTo(view.getParent());
        view.setVisible(true);
    }

    public IncluirContatoPresenter(ContatoCollection contatos, Contato contato, String titulo, ListarContatosPresenter lcp) {
        this.contatos = contatos;
        view = new IncluirContatoView();

        view.getBtnSalvar().setText("Editar");
        view.setTitle(titulo);

        view.getTxtNome().setText(contato.getNome());
        view.getTxtTelefone().setText(contato.getTelefone());

        view.getTxtNome().setEditable(false);
        view.getTxtTelefone().setEditable(false);

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            editar(contato, lcp);
        });

        view.setLocationRelativeTo(view.getParent());
        view.setVisible(true);

    }

    private void editar(Contato contato, ListarContatosPresenter lcp) {
        view.getTxtNome().setEditable(true);
        view.getTxtTelefone().setEditable(true);

        view.getTxtNome().setText(contato.getNome());
        view.getTxtTelefone().setText(contato.getTelefone());

        view.getBtnSalvar().setText("Salvar");
        view.setTitle("Editar contato");

        for (ActionListener al : view.getBtnSalvar().getActionListeners()) {
            view.getBtnFechar().removeActionListener(al);
        }
        for (ActionListener al : view.getBtnSalvar().getActionListeners()) {
            view.getBtnFechar().removeActionListener(al);
        }

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            salvar(contato, lcp);
        });

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar();
        });

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

    private void salvar(Contato contato, ListarContatosPresenter lcp) {
        Contato novo = new Contato(view.getTxtNome().getText(),
                view.getTxtTelefone().getText());

        if (!contato.equals(novo)) {
            contatos.update(contato, novo);
            contatos.ordena();

            JOptionPane.showMessageDialog(view, "Contato atualizado com sucesso!");
        }
        lcp.updateView();
        fechar();

    }
}
