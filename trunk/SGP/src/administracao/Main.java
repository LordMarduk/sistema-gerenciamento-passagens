package administracao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import administracao.database.DataBaseManagerImpl;
import administracao.gui.MenuPrincipal;
import javax.swing.JFrame;

public class Main {

    public static DataBaseManagerImpl dbm;

    public static void main(String args[]) {

        /*
          C:\Users\Jader>cd C:\Users\Jader\Documents\NetBeansProjects\SGP\build\classes

        C:\Users\Jader\Documents\NetBeansProjects\SGP\build\classes>rmic administracao.d
        atabase.DataBaseManagerImpl

         C:\Users\Jader\Documents\NetBeansProjects\SGP\build\classes>rmiregistry

         */

        try {

            System.out.println("Ligando Servidor...");

            dbm = new DataBaseManagerImpl();

            Naming.rebind("rmi://localhost:1099/DataBaseManagerService", dbm);

            System.out.println("... Servidor Ligado!");

            MenuPrincipal mp = new MenuPrincipal(dbm);
            mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mp.setSize(800, 600);
            mp.setResizable(false);
            mp.setLocationRelativeTo(null);
            mp.setVisible(true);

        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

    }
}
