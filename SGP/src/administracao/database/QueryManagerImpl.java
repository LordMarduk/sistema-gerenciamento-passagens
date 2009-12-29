package administracao.database;

import util.QueryManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class QueryManagerImpl extends UnicastRemoteObject implements QueryManager {

    private static final String JDBC_DRIVER =
            "org.postgresql.Driver";
    private static final String DATABASE_URL =
            "jdbc:postgresql://localhost:5432/SGP";
    private static final String USER =
            "postgres";
    private static final String PASSWORD =
            "123456";

    private Connection connection;

    private String query;
    private Statement stm;
    private ResultSet res;
    private ResultSetMetaData met;

    public QueryManagerImpl() throws RemoteException {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Exception! hehe", "Erro", 0);
        //System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Class Not Found Exception!", "Erro", 0);
            closeConnection();
        //System.exit(1);
        }

    }

    public synchronized Connection getConnection() throws RemoteException {
        return this.connection;
    }

    public boolean closeConnection() throws RemoteException {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Exception!", "Erro", 0);
            return false;
        }
    }

//     FUNCOES DE BUSCA
    public synchronized void setQuery(String query) throws RemoteException {
        try {
            stm = connection.createStatement(1004, 1007);
            //TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY
            res = stm.executeQuery(query);
            met = res.getMetaData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized int queryRowCount(String myQuery) throws RemoteException {
        if( !myQuery.equals(query) ) setQuery(myQuery);
        try {
            res.last();
            return res.getRow();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public synchronized int queryColumnCount(String myQuery) throws RemoteException {
        if( !myQuery.equals(query) ) setQuery(myQuery);
        try {
            return met.getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public synchronized Class queryColumnClass(int column, String myQuery) throws RemoteException{
        if( !myQuery.equals(query) ) setQuery(myQuery);
        try {
            String className = met.getColumnClassName(column);
            return Class.forName(className);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return Object.class;
    }

    public synchronized Object queryValueAt(int row, int col, String myQuery) throws RemoteException {
        if( !myQuery.equals(query) ) setQuery(myQuery);
        try{
            res.absolute(row);
            return res.getObject(col);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}