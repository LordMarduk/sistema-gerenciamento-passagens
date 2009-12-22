package administracao.database;

import administracao.tipo_de_viagem.TipoDeViagem;
import cliente.Cliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.DataBaseManager;

/**
 *
 * @author Marcio Passos
 * @since 3 de dezembro de 2009
 * Manipulacao do Banco de Dados
 *
 */
public class DataBaseManagerImpl extends UnicastRemoteObject implements DataBaseManager {

    public static final String JDBC_DRIVER =
            "org.postgresql.Driver";
    public static final String DATABASE_URL =
            "jdbc:postgresql://localhost:5432/SGP";
    public Connection connection;

    public DataBaseManagerImpl() throws RemoteException {

        try {

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");

        } catch (SQLException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Exception!", "Erro", 0);
            //System.exit(1);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Class Not Found Exception!", "Erro", 0);
            closeConnection();
            //System.exit(1);

        }

    }

//     FUNCOES DE CLIENTE
    public boolean insertCliente(Cliente novoCliente) throws RemoteException {

        return insertCliente(novoCliente.stringToQuery());

    }

    public boolean insertCliente(String novoCliente) throws RemoteException {

        Statement stm = null;
        try {

            stm = connection.createStatement();
            int i = stm.executeUpdate("INSERT INTO cliente VALUES" + novoCliente);
            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Exception!", "Erro", 0);
            return false;
            //System.exit(1);

        }

    }

    public int maximunValueCliente() throws RemoteException {

        Statement stm = null;
        try {

            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT max(codigo_cli) FROM cliente");
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Exception!", "Erro", 0);
            return -1;
            //System.exit(1);

        }

    }

//    FUNCOES DE TIPO DE VIAGEM
    public TipoDeViagem selectTipoDeViagem(int id_seq_tdv) throws RemoteException {

        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from tipo_de_viagem where id_seq_tdv = " + id_seq_tdv;
        TipoDeViagem tdv = new TipoDeViagem();

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            rs.next();
            tdv.setIdSeqTdv(rs.getInt("id_seq_tdv"));
            tdv.setValorViagem(rs.getFloat("valor_viagem"));
            tdv.setHoraPrevSaida(rs.getString("hora_prev_saida"));
            tdv.setHoraPrevChegada(rs.getString("hora_prev_chegada"));
            tdv.setDiasDaSemana(rs.getString("dias_da_semana"));
            tdv.setIdSeqRodovPartida(rs.getInt("id_seq_rodov_partida"));
            tdv.setIdSeqRodovChegada(rs.getInt("id_seq_rodov_chegada"));

            rs.close();
            st.close();
            return tdv;
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
        return null;
    }

    public void deleteTipoDeViagem(int id_seq_tdv) throws RemoteException {
        Statement st = null;
        String sql = "delete from tipo_de_viagem where id_seq_tdv = " + id_seq_tdv;

        try {

            st = connection.createStatement();
            System.out.println("executando: " + sql);
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }


    }

    public void updateTipoDeViagem(int id_seq_tdv, TipoDeViagem novoTdv) throws RemoteException {

        PreparedStatement pst = null;

        String sql = "update tipo_de_viagem set valor_viagem = ?,hora_prev_saida = ?," +
                "hora_prev_chegada = ?,dias_da_semana = ?,id_seq_rodov_partida = ?," +
                "id_seq_rodov_chegada = ? where id_seq_tdv = " + id_seq_tdv;
        try {
            pst = connection.prepareStatement(sql);

            pst.setFloat(1, novoTdv.getValorViagem());
            pst.setString(2, novoTdv.getHoraPrevSaida());
            pst.setString(3, novoTdv.getHoraPrevChegada());
            pst.setString(4, novoTdv.getDiasDaSemana());
            pst.setInt(5, novoTdv.getIdSeqRodovPartida());
            pst.setInt(6, novoTdv.getIdSeqRodovChegada());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }

    public void insertTipoDeViagem(TipoDeViagem tdv) throws RemoteException {
        try {
            PreparedStatement pst = null;
            //ResultSet rs = null;

            String sql = "insert into tipo_de_viagem(valor_viagem,hora_prev_saida," +
                    "hora_prev_chegada,dias_da_semana,id_seq_rodov_partida," +
                    "id_seq_rodov_chegada) values(?,?,?,?,?,?) ";

            pst = connection.prepareStatement(sql);

            pst.setFloat(1, tdv.getValorViagem());
            pst.setString(2, tdv.getHoraPrevSaida());
            pst.setString(3, tdv.getHoraPrevChegada());
            pst.setString(4, tdv.getDiasDaSemana());
            pst.setInt(5, tdv.getIdSeqRodovPartida());
            pst.setInt(6, tdv.getIdSeqRodovChegada());

            pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }

//    FUNCOES VALIDACAO DE ACESSO
    public boolean validaEntradaAgente(String nome, String senha) throws RemoteException {

        Statement stmt;
        try {
            stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery("select senha from agente WHERE usuario = '" + nome + "' ORDER BY usuario");

            if (!resultSet.next()) {
                return false;
            }

            String senhaBanco = resultSet.getString("senha");

            if (senhaBanco.compareTo(senha) == 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }

//    FECHAR CONEXAO
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
}
