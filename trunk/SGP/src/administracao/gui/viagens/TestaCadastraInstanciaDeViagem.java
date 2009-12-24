/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package administracao.gui.viagens;

import java.rmi.RemoteException;
import javax.swing.JFrame;
import administracao.database.DataBaseManagerImpl;

/**
 *
 * @author Jader
 */
public class TestaCadastraInstanciaDeViagem extends JFrame{

    static DataBaseManagerImpl dbm = null;

    public static void main(String args[]) throws RemoteException
    {
        dbm = new DataBaseManagerImpl();
         CadastraInstanciaDeViagem ctdv = new CadastraInstanciaDeViagem(0,dbm);
         ctdv.setVisible(true);
         ctdv.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
