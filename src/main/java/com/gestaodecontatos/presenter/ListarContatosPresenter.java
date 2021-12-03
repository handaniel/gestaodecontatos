package com.gestaodecontatos.presenter;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.model.Contato;
import com.gestaodecontatos.view.ListarContatosView;
import java.awt.event.ActionEvent;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author handaniels
 */
public class ListarContatosPresenter {

    private ListarContatosView view;
    private ContatoCollection contatos;
    private DefaultTableModel tmContatos;

    public ListarContatosPresenter(ContatoCollection contatos) {
        this.contatos = contatos;
        view = new ListarContatosView();

        tmContatos = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Telefone"}
        );

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar();
        });

        view.getBtnVisualizar().addActionListener((ActionEvent ae) -> {
            visualizar();
        });

        view.getBtnExcluir().addActionListener((ActionEvent ae) -> {
            if (view.getTblContatos().getSelectedRowCount() != 0) {
                excluir();
            } else {
                JOptionPane.showMessageDialog(view, "Selecione uma linha!");
            }
        });

        view.getCkbOrdenarTelefone().addActionListener((ActionEvent ae) -> {
            ordenaTelefone();
        });

        updateView();

    }

    private void ordenaTelefone() {
        TableRowSorter sorter = new TableRowSorter(tmContatos);
        view.getTblContatos().setRowSorter(sorter);

        if (view.getCkbOrdenarTelefone().isSelected()) {
            sorter.toggleSortOrder(1);
        } else {
            sorter.toggleSortOrder(0);
        }

    }

    private void updateView() {
        tmContatos.setRowCount(0);

        view.getTblContatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListIterator<Contato> it = contatos.getContatos().listIterator();

        while (it.hasNext()) {
            Contato contato = it.next();
            tmContatos.addRow(new Object[]{contato.getNome(), contato.getTelefone()});
        }

        view.getLblQuantidade().setText(Integer.toString(contatos.getContatos().size()));

        view.getTblContatos().setModel(tmContatos);
        view.setLocationRelativeTo(view.getParent());
        view.setVisible(true);
    }

    private void fechar() {
        view.dispose();
    }

    private void visualizar() {

    }

    private void excluir() {

        String nome = view.getTblContatos().getValueAt(view.getTblContatos().getSelectedRow(), 0).toString();
        String telefone = view.getTblContatos().getValueAt(view.getTblContatos().getSelectedRow(), 1).toString();

        int confirm = JOptionPane.showConfirmDialog(view, "Confirmar exclusão do seguinte contato?"
                + "\nNome: " + nome + "\nTelefone: " + telefone, "Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Contato del = contatos.find(nome, telefone);

            contatos.getContatos().remove(del);

            updateView();
        }

    }

}
