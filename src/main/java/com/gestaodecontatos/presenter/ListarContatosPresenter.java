package com.gestaodecontatos.presenter;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.model.Contato;
import com.gestaodecontatos.view.ListarContatosView;
import java.awt.event.ActionEvent;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

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
        
        updateView(tmContatos, contatos);
        
        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar();
        });
        
        view.getBtnVisualizar().addActionListener((ActionEvent ae) -> {
            visualizar();
        });
        
        view.getBtnExcluir().addActionListener((ActionEvent ae) -> {
            excluir();
        });
        //view.getCkbOrdenarTelefone().get

        view.setLocationRelativeTo(view.getParent());
        view.setVisible(true);
        
    }
    
    private void updateView(DefaultTableModel tmContatos, ContatoCollection contatos) {
        view.getTblContatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListIterator<Contato> it = contatos.getContatos().listIterator();
        
        while (it.hasNext()) {
            Contato contato = it.next();
            tmContatos.addRow(new Object[]{contato.getNome(), contato.getTelefone()});
        }
        
        view.getTblContatos().setModel(tmContatos);
    }
    
    private void fechar() {
        view.dispose();
    }
    
    private void visualizar() {
        
    }
    
    private void excluir() {
        JOptionPane.showMessageDialog(view, "Excluir ainda não implementado!");
    }
    
}
