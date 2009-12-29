package util;

import java.rmi.RemoteException;
import javax.swing.table.AbstractTableModel;
public class TableModelCliente extends AbstractTableModel {

    private final QueryManager qm;
    
    private String query;

    public static final String[] CLIENTE_COLUMNS_NAMES = {"ID", "Nome", "Sexo", "Data de Nascimento",
        "CPF", "Endereço", "Telefone", "Estudante"};

    public TableModelCliente(final QueryManager qm, String query){
        this.qm = qm;
        this.query = query;
    }

    public int getRowCount() {
        try {
            return qm.queryRowCount(query);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int getColumnCount() {
        return 8;
        /*
         try {
            return dbm.queryColumnCount(query);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return -1;
        */
    }

/*
    @Override
    public Class getColumnClass(int column){        
        try {
            return dbm.queryColumnClass(column + 1, query);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return Object.class;
    }
*/

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Object o = qm.queryValueAt(rowIndex + 1, columnIndex + 1, query);
            return o;
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return CLIENTE_COLUMNS_NAMES[column];
    }

    public void setQuery(String newQuery) {
        this.query = newQuery;
        try {
            qm.setQuery(query);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        fireTableStructureChanged();
    }

}
