package util;

import java.rmi.RemoteException;
import javax.swing.table.AbstractTableModel;
public class TableModelII extends AbstractTableModel {

    private final QueryManager qm;

    private String query;

    public String[] columnsNames;

    public TableModelII(final QueryManager qm, String query, String[] columnsNames){
        this.qm = qm;
        this.query = query;
        this.columnsNames = columnsNames;
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
        return columnsNames.length;
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
        return columnsNames[column];
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