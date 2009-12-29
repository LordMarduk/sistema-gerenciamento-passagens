package administracao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import administracao.database.DataBaseManagerImpl;
import administracao.database.QueryManagerImpl;
import administracao.gui.MenuPrincipal;
import javax.swing.JFrame;
import javax.swing.UIManager;

/*
C:\Users\Jader>cd C:\Users\Jader\Documents\NetBeansProjects\SGP\build\classes

C:\Users\Jader\Documents\NetBeansProjects\SGP\build\classes>rmic administracao.database.DataBaseManagerImpl

C:\Users\Jader\Documents\NetBeansProjects\SGP\build\classes>rmiregistry

 */
public class Main {

    public static DataBaseManagerImpl dbm;
    public static QueryManagerImpl qm;

    public static void main(String args[]) {

        try {

            System.out.println("Ligando Servidor...");

            dbm = new DataBaseManagerImpl();
            qm = new QueryManagerImpl();

            Naming.rebind("rmi://localhost:1099/DataBaseManagerService", dbm);
            Naming.rebind("rmi://localhost:1099/QueryManagerService", qm);

            System.out.println("... Servidor Ligado!");

        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        MenuPrincipal mp = new MenuPrincipal(dbm);
        mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
