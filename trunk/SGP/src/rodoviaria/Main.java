package rodoviaria;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import rodoviaria.gui.NovoCliente;
import util.DataBaseManager;
import javax.swing.UIManager;

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

        NovoCliente ncf = new NovoCliente(dbm);

    }

}
