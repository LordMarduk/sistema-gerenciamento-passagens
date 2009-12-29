package administracao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import administracao.database.DataBaseManagerImpl;
import administracao.database.QueryManagerImpl;
import administracao.gui.MenuPrincipal;
import cliente.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import rodoviaria.gui.GerenciamentoDeClientes;
import util.QueryManager;

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

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //MenuPrincipal mp = new MenuPrincipal(dbm);
        //mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //GerenciamentoDeClientes gc = new GerenciamentoDeClientes(dbm, qm);

        try {
            Cliente buh = dbm.getCliente(8);
            System.out.println(buh);
            buh.setNome("buh");
            dbm.updateCliente(8, buh);
            buh = dbm.getCliente(8);
            System.out.println(buh);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
