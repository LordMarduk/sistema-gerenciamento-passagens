/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rodoviaria.gui.passagem;

/**
 *
 * @author Jader
 */
import administracao.database.DataBaseManagerImpl;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.JFrame;

public class TestaGerenciaPassagem extends JFrame {

    public static DataBaseManagerImpl dbm;


    public static void main(String args[]) throws RemoteException {

        try {
        dbm = new DataBaseManagerImpl();
        }
        catch (RemoteException ex) {
            ex.printStackTrace();
        }

        GerenciaPassagem gp = new GerenciaPassagem(dbm);
        gp.setVisible(true);
    }
}
