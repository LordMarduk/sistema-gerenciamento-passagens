package rodoviaria;

import cliente.Cliente;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rodoviaria.gui.NovoCliente;
import util.DataBaseManager;
import javax.swing.UIManager;
import util.Date;

public class Main {

    public static DataBaseManager dbm;

    public static void main(String args[]){

        try {
            dbm = (DataBaseManager) Naming.lookup("rmi://localhost/DataBaseManagerService");
        }
        catch (NotBoundException ex) {
            ex.printStackTrace();
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        catch (RemoteException ex) {
            ex.printStackTrace();
        }

        UIManager.LookAndFeelInfo[] lafs =  UIManager.getInstalledLookAndFeels();
        try {
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        NovoCliente ncframe = new NovoCliente(dbm);

    }

}
