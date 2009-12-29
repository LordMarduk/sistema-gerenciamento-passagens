package util;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;

public interface QueryManager extends Remote{

    public Connection getConnection() throws RemoteException ;

    public boolean closeConnection() throws RemoteException;

    public void setQuery(String query) throws RemoteException;

    public int queryRowCount(String myQuery) throws RemoteException;

    public int queryColumnCount(String myQuery) throws RemoteException;

    public Class queryColumnClass(int column, String myQuery) throws RemoteException;

    public Object queryValueAt(int row, int col, String myQuery) throws RemoteException;

}
