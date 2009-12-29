
package util;

//Um TableModel que fornece dados ResultSet a uma JTable.
import administracao.gui.viagens.*;
import administracao.database.DataBaseManagerImpl;
import java.rmi.RemoteException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

//jtable indice começa do 0/ resultset começa do 1
public class TableModel extends AbstractTableModel {

    // monitora o status da conex�o de banco de dados
    private boolean connectedToDatabase = false;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;

    public final DataBaseManagerImpl dbm;

    public TableModel(DataBaseManagerImpl dbm, String consulta)
            throws SQLException, ClassNotFoundException
    {

        this.dbm = dbm;
        try {
            // cria Statement para consultar banco de dados
            statement = dbm.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

        // atualiza status de conex�o de banco de dados
        connectedToDatabase = true;

        // configura consulta e a executa
        //setQuery("SELECT * FROM tipo_de_viagem");
        setQuery(consulta);
    } // fim do construtor TabelaFilme

    //tipo das colunas(integer,...etc)
    @Override
    public Class getColumnClass(int column) throws IllegalStateException {
        // assegura que o banco de dados conex�o est� dispon�vel
        if (!connectedToDatabase) {
            throw new IllegalStateException("N�o conectado � Database");
        }

        // determina a classe Java de coluna
        try {
            String className = metaData.getColumnClassName(column + 1);

            // retorna objeto Class que representa className
            return Class.forName(className);
        } // fim do try
        catch (Exception exception) {
            exception.printStackTrace();
        } // fim do catch

        return Object.class; // se ocorrerem os problemas acima, assume tipo Object
    } // fim do m�todo getColumnClass

    // numero de colunas
    public int getColumnCount() throws IllegalStateException {
        // assegura que o banco de dados conex�o est� dispon�vel
        if (!connectedToDatabase) {
            throw new IllegalStateException("N�o conectado � Database");
        }

        // determina n�mero de colunas
        try {
            return metaData.getColumnCount();
        } // fim do try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // fim do catch

        return 0; // se ocorrerem os problemas acima, retorna 0 para o n�mero de colunas
    } // fim do m�todo getColumnCount

    //nomes das colunas
    @Override
    public String getColumnName(int column) throws IllegalStateException {
        // assegura que o banco de dados conex�o est� dispon�vel
        if (!connectedToDatabase) {
            throw new IllegalStateException("N�o conectado � Database");
        }

        // determina o nome de coluna
        try {
            return metaData.getColumnName(column + 1);
        } // fim do try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // fim do catch

        return ""; // se ocorrerem problemas, retorna string vazia para nome de coluna
    } // fim do m�todo getColumnName

    // numero de linhas do ResultSet
    public int getRowCount() throws IllegalStateException {
        // assegura que o banco de dados conex�o est� dispon�vel
        if (!connectedToDatabase) {
            throw new IllegalStateException("N�o conectado � Database");
        }

        return numberOfRows;
    } // fim do m�todo getRowCount

    // valor de acordo com linha e coluna
    public Object getValueAt(int row, int column)
            throws IllegalStateException {
        // assegura que o banco de dados conex�o est� dispon�vel
        if (!connectedToDatabase) {
            throw new IllegalStateException("N�o conectado � Database");
        }

        // obt�m um valor na linha e coluna de ResultSet especificada
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } // fim do try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // fim do catch

        return ""; // se ocorrerem problemas, retorna objeto string vazio
    } // fim do m�todo getValueAt

// configura nova string de consulta de banco de dados
    public void setQuery(String query)
            throws SQLException, IllegalStateException
    {
        // assegura que o banco de dados conex�o est� dispon�vel
        if (!connectedToDatabase) {
            throw new IllegalStateException("N�o conectado � Database");
        }

        // especifica consulta e a executa
        resultSet = statement.executeQuery(query);

        // obt�m metadados para ResultSet
        metaData = resultSet.getMetaData();

        // determina o n�mero de linhas em ResultSet
        resultSet.last();                   // move para a �ltima linha
        numberOfRows = resultSet.getRow();  // obt�m n�mero de linha

        // notifica a JTable de que modelo foi alterado
        fireTableStructureChanged();
    } // fim do m�todo setQuery
    
} 


