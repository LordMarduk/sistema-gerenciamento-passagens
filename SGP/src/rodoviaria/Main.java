package rodoviaria;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import util.DataBaseManager;
import javax.swing.UIManager;
import rodoviaria.cliente.Cliente;
import rodoviaria.gui.Login;
import rodoviaria.gui.cliente.EditarCliente;
import util.QueryManager;

public class Main {

    public static DataBaseManager dbm;
    public static QueryManager qm;

    public static void main(String args[]) {

        try {
            dbm = (DataBaseManager) Naming.lookup("rmi://localhost/DataBaseManagerService");
            qm = (QueryManager) Naming.lookup("rmi://localhost/QueryManagerService");
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Login loginframe = new Login(dbm, qm);
    //NovoCliente ncframe = new NovoCliente(dbm);
    //GerenciamentoDeClientes gcframe = new GerenciamentoDeClientes(dbm);

    }
}
