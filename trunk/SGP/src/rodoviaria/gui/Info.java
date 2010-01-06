package rodoviaria.gui;

import java.awt.Color;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import rodoviaria.FileManager;
import rodoviaria.rodoviaria.Rodoviaria;
import util.DataBaseManager;

public class Info extends JFrame {

    private final DataBaseManager dbm;

    public Info(DataBaseManager dbm) {

        super("Informações");
        setBounds(500, 300, 300, 200);
        setDefaultCloseOperation(2);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        this.dbm = dbm;

        int id = FileManager.lerId().getId();
        Rodoviaria essa = null;
        try {
            essa = dbm.getRodoviaria(id);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

        JTextArea info = new JTextArea( "\n" + essa.toString() );
        info.setBounds(50, 30, 200, 115);
        info.setEditable(false);
        info.setBackground( new Color(214, 217, 223) );
        add(info);

        repaint();

    }

}
