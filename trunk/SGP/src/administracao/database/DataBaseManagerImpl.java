package administracao.database;


import administracao.funcionario.Funcionario;
import cliente.Cliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import util.DataBaseManager;
import administracao.viagens.InstanciaDeViagem;
import administracao.viagens.TipoDeViagem;

/**
 *
 * @author Marcello Passos
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

            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");

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

//     FUNCOES DE CLIENTE
    public boolean insertCliente(Cliente novoCliente) throws RemoteException {

        String sql =
            "INSERT INTO cliente " +
                "(id_seq_cliente, nome, sexo, data_nascimento, cpf, endereco," +
                " telefone, e_estudante)" +
            "VALUES(" +
                novoCliente.getCodigo_cli() + ", '" +
                novoCliente.getNome() + "' , '" +
                novoCliente.getSexo() + "' , " +
                "to_date('" + novoCliente.getData_nascimento() + "', 'DD/MM/YYYY'), '" +
                novoCliente.getCpf() + "' , '" +
                novoCliente.getEndereco() + "' , '" +
                novoCliente.getTelefone() + "' , " +
                novoCliente.isEEstudante() +
            ")";

        System.out.println(sql);

        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public int maximunValueCliente() throws RemoteException {

        Statement stm = null;
        try {

            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT max(id_seq_cliente) FROM cliente");
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

    /////////////////////////////FUNCOES DE INSTANCIA DE VIAGEM///////////////////////////////
    public InstanciaDeViagem selectInstanciaDeViagem(int id_seq_tdv,Date data) {

        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from instancia_de_viagem where id_seq_tdv = "
        	+ id_seq_tdv + " AND data = " + data;
        InstanciaDeViagem idv = new InstanciaDeViagem();

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            rs.next();
            idv.setIdSeqTdv(rs.getInt("id_seq_tdv"));
            idv.setNumVagasDisponiveis(rs.getInt("num_vagas_disponiveis"));
            idv.setHoraRealSaida(rs.getString("hora_real_saida"));
            idv.setHoraRealChegada(rs.getString("hora_real_chegada"));
            idv.setData(rs.getDate("data"));//ou timstamp testar depois
            idv.setIdSeqCarro(rs.getInt("id_seq_carro"));
            idv.setIdSeqMotorista(rs.getInt("id_seq_motorista"));
            idv.setObservacoes(rs.getString("observacoes"));

            rs.close();
            st.close();
            return idv;
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
        return null;
    }

    public void deleteInstanciaDeViagem(int id_seq_idv, Date data) {
        Statement st = null;
        String sql = "delete from instancia_de_viagem where id_seq_idv = "
        	+ id_seq_idv + " AND data = " + data;

        try {

            st = connection.createStatement();
            System.out.println("executando: "+ sql);
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }


    }

    public void updateInstanciaDeViagem(int id_seq_idv,Date data,
    		InstanciaDeViagem novoIdv) {

        PreparedStatement pst = null;

        String sql = "update instancia_de_viagem set id_seq_tdv = ?," +
        		"num_vagas_disponiveis = ?,hora_real_saida = ?," +
        				"hora_real_chegada = ?,data = ?," +
        				"id_seq_carro = ?,id_seq_motorista = ?," +
        				"obeservacoes = ?, " +
        				"where id_seq_idv = " + id_seq_idv
        				+ " AND data = " + data;
        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1,novoIdv.getIdSeqTdv());
            pst.setInt(2,novoIdv.getNumVagasDisponiveis());
            pst.setString(3,novoIdv.getHoraRealSaida());
            pst.setString(4,novoIdv.getHoraRealChegada());
            pst.setDate(5,(java.sql.Date)novoIdv.getData());
            pst.setInt(6,novoIdv.getIdSeqCarro());
            pst.setInt(7,novoIdv.getIdSeqMotorista());
            pst.setString(8,novoIdv.getObservacoes());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }

    public void insertInstanciaDeViagem(InstanciaDeViagem idv) {
        try {
            PreparedStatement pst = null;
            //ResultSet rs = null;

            String sql = "insert into instancia_de_viagem(id_seq_tdv," +
            		"num_vagas_disponiveis,hora_real_saida," +
                    "hora_real_chegada,data,id_seq_carro," +
                    "id_seq_motorista,observacoes) values(?,?,?,?,?,?,?,?) ";

            pst = connection.prepareStatement(sql);

            pst.setInt(1, idv.getIdSeqTdv());
            pst.setInt(2, idv.getNumVagasDisponiveis());
            pst.setString(3, idv.getHoraRealSaida());
            pst.setString(4, idv.getHoraRealChegada());
            pst.setTimestamp(5, new java.sql.Timestamp(idv.getData().getTime()));
            pst.setInt(6, idv.getIdSeqCarro());
            pst.setInt(7, idv.getIdSeqMotorista());
            pst.setString(8, idv.getObservacoes());

            pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////

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

    //////////////////////////////////////////////////////////////////
    public void insertAgente(Funcionario novoAgente) throws RemoteException {

        System.out.println(novoAgente.getNome());
        //System.out.println(novoAgente.getSexo());
        //System.out.println(novoAgente.getDatanascimento());
        System.out.println(novoAgente.getCpf());
        System.out.println(novoAgente.getTelefone());
        System.out.println(novoAgente.getEndereco());
    }
    /////////////////////////////////////////////////////////////////

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
