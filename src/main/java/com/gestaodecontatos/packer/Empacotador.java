package com.gestaodecontatos.packer;

import com.gestaodecontatos.collections.ContatoCollection;
import com.gestaodecontatos.model.Contato;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Daniel Hand Santiago 2018200011
 */
public class Empacotador {

    public Empacotador() {
    }

    public static void serializar(File arq, ContatoCollection contatos) throws Exception {
        arq.delete();
        arq.createNewFile();
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arq));
        obj.writeObject(contatos.getContatos());
        obj.close();

    }

    public static ArrayList<Contato> deserializar(File arq) throws Exception {
        ArrayList<Contato> contatos = new ArrayList<Contato>();

        ObjectInputStream obj = new ObjectInputStream(new FileInputStream(arq));
        contatos = (ArrayList<Contato>) obj.readObject();
        obj.close();

        return contatos;
    }

}
