package administracao.database;

import administracao.carro.Carro;
import administracao.funcionario.Funcionario;
import rodoviaria.cliente.Cliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import util.DataBaseManager;
import administracao.viagens.InstanciaDeViagem;
import administracao.viagens.TipoDeViagem;
import java.text.ParseException;
import rodoviaria.rodoviaria.Rodoviaria;
import util.Date;

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
    public synchronized boolean insertCliente(Cliente novoCliente) throws RemoteException {
        String sql =
            "INSERT INTO cliente " +
                "(id_seq_cliente, nome, sexo, data_nascimento, cpf, endereco," +
                " telefone, e_estudante)" +
            "VALUES(" +
                novoCliente.getId() + ", '" +
                novoCliente.getNome() + "' , '" +
                novoCliente.getSexo() + "' , " +
                "to_date('" + novoCliente.getData_nascimento() + "', 'DD/MM/YYYY'), '" +
                novoCliente.getCpf() + "' , '" +
                novoCliente.getEndereco() + "' , '" +
                novoCliente.getTelefone() + "' , " +
                novoCliente.isEEstudante() +
            ")";
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public synchronized Cliente getCliente(int id) throws RemoteException {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM cliente WHERE id_seq_cliente = " + id);
            rs.next();
            if( !rs.wasNull() ){
                Cliente sel = new Cliente(id, rs.getString(2), rs.getString(3).charAt(0),
                        new Date( rs.getString(4) ), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getBoolean(8));
                connection.close();
                return sel;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public synchronized boolean updateCliente(int id, Cliente mod) throws RemoteException{
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            String sql =
                "UPDATE cliente SET " +
                    "nome = '" + mod.getNome() + "', " +
                    "sexo = '" + mod.getSexo() + "', " +
                    "data_nascimento = " + mod.getData_nascimento().stringToQuery() + ", " +
                    "cpf = '" + mod.getCpf() + "', " +
                    "endereco = '" + mod.getEndereco() + "', " +
                    "telefone = '" + mod.getTelefone() + "', " +
                    "e_estudante = " + mod.isEEstudante() + " " +
                "WHERE id_seq_cliente = " + mod.getId();
            stm.executeUpdate(sql);
            stm.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public synchronized boolean deleteCliente(int id) throws RemoteException{
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            stm.executeUpdate("DELETE FROM cliente WHERE id_seq_cliente = " + id);
            stm.close();
            connection.close();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public synchronized int maximunValueCliente() throws RemoteException {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT max(id_seq_cliente) FROM cliente");
            rs.next();
            int m = rs.getInt(1);
            stm.close();
            connection.close();
            return m;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

//    FUNCOES DE TIPO DE RODOVIARIA
    public synchronized int maximunValueRodoviaria() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT max(id_seq_rodov) FROM rodoviaria");
            rs.next();
            int m = rs.getInt(1);
            stm.close();
            connection.close();
            return m;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public synchronized boolean insertRodoviaria(Rodoviaria novaRodoviaria) {
        String sql =
            "INSERT INTO rodoviaria " +
                "(id_seq_rodov, cidade, estado, telefone)" +
            "VALUES(" +
                novaRodoviaria.getId() + ", '" +
                novaRodoviaria.getCidade() + "' , '" +
                novaRodoviaria.getEstado() + "' , " +
                novaRodoviaria.getTelefone() +
            ")";
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public synchronized Rodoviaria getRodoviaria(int id) throws RemoteException{
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM rodoviaria WHERE id_seq_rodov = " + id);
            rs.next();
            if( !rs.wasNull() ){
                Rodoviaria sel = new Rodoviaria( id, rs.getString(2), rs.getString(3), rs.getString(4) );
                connection.close();
                return sel;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

        public synchronized boolean updateRodoviaria(int id, Rodoviaria mod) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            String sql =
                "UPDATE rodoviaria SET " +
                    "cidade = '" + mod.getCidade() + "', " +
                    "estado = '" + mod.getEstado() + "', " +
                    "telefone = '" + mod.getTelefone() + "' " +
                "WHERE id_seq_rodov = " + mod.getId();
            stm.executeUpdate(sql);
            stm.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public synchronized boolean deleteRodoviaria(int id){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stm = connection.createStatement();
            stm.executeUpdate("DELETE FROM rodoviaria WHERE id_seq_rodov = " + id);
            stm.close();
            connection.close();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
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
    public InstanciaDeViagem selectInstanciaDeViagem(int id_seq_tdv, String data) throws ParseException {

        Statement st = null;
        ResultSet rs = null;

        String dtaa = data;

        //Recebe a data do BD da forma yyyy-mm-dd
        //java.util.Date dt = new SimpleDateFormat("yyyy-mm-dd").parse(data);

        //String sql = "select * from instancia_de_viagem where id_seq_tdv = " + id_seq_tdv + " AND data = to_date('" + new SimpleDateFormat("dd/MM/yyyy").format(dt) + "', 'DD/MM/YYYY')";

        String sql = "select * from instancia_de_viagem where id_seq_tdv = " + id_seq_tdv + " AND data = '" + data + "'";

        //String sql = "select * from instancia_de_viagem where id_seq_tdv = "
        //+ id_seq_tdv;


        //select * from instancia_de_viagem where id_seq_tdv = 23  AND data = to_date('01/11/2001' ,'DD/MM/YYYY');

        InstanciaDeViagem idv = new InstanciaDeViagem();

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            rs.next();

            idv.setIdSeqTdv(rs.getInt("id_seq_tdv"));
            idv.setNumVagasDisponiveis(rs.getInt("num_vagas_disponiveis"));
            idv.setHoraRealSaida(rs.getString("hora_real_saida"));
            idv.setHoraRealChegada(rs.getString("hora_real_chegada"));

            //Recebe a data do BD da forma yyyy-mm-dd
            //java.util.Date dt = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("data"));
            //passa a data formatada dd/MM/yyyy
            //idv.setData(new SimpleDateFormat("dd/MM/yyyy").format(dt));
            idv.setData(rs.getString("data"));


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

    public void deleteInstanciaDeViagem(int id_seq_idv, String data) {
        Statement st = null;

        String sql = "delete from instancia_de_viagem where id_seq_tdv = " + id_seq_idv + " AND data = '" + data + "'";

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

    public void updateInstanciaDeViagem(int id_seq_idv, String data,
            InstanciaDeViagem novoIdv) {

        PreparedStatement pst = null;

        String sql = "update instancia_de_viagem set id_seq_tdv = ?," +
                "num_vagas_disponiveis = ?,hora_real_saida = ?," +
                "hora_real_chegada = ?,data = to_date(?,'DD/MM/YYYY')," +
                "id_seq_carro = ?,id_seq_motorista = ?," +
                "observacoes = ? " +
                "where id_seq_tdv = " + id_seq_idv + " AND data = '" + data + "'";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, novoIdv.getIdSeqTdv());
            pst.setInt(2, novoIdv.getNumVagasDisponiveis());
            pst.setString(3, novoIdv.getHoraRealSaida());
            pst.setString(4, novoIdv.getHoraRealChegada());
            pst.setString(5, novoIdv.getData());
            pst.setInt(6, novoIdv.getIdSeqCarro());
            pst.setInt(7, novoIdv.getIdSeqMotorista());
            pst.setString(8, novoIdv.getObservacoes());

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
                    "id_seq_motorista,observacoes) values(?,?,?,?,to_date(?,'DD/MM/YYYY'),?,?,?) ";

            pst = connection.prepareStatement(sql);

            pst.setInt(1, idv.getIdSeqTdv());
            pst.setInt(2, idv.getNumVagasDisponiveis());
            pst.setString(3, idv.getHoraRealSaida());
            pst.setString(4, idv.getHoraRealChegada());
            pst.setString(5, idv.getData());
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
    ////////////////////////////////////////////////////////////////////////////

//    FUNCOES VALIDACAO DE ACESSO
    public boolean validaEntradaAgente(String nome, String senha) throws RemoteException {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "123456");
            Statement stmt = connection.createStatement();
            ResultSet resultSet =
                    stmt.executeQuery("select senha from agente WHERE usuario = '" + nome + "' ORDER BY usuario");
            if (resultSet.next()){
                String senhaBanco = resultSet.getString("senha");
                connection.close();
                return senhaBanco.equals(senha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    ///////////FUNÇÕES DE AGENTE///////////////////
    public void insertAgente(Funcionario novoAgente) throws RemoteException, ParseException {

        /*
        System.out.println(novoAgente.getNome());
        System.out.println(novoAgente.getSexo());
        System.out.println(novoAgente.getDatanascimento());
        System.out.println(novoAgente.getCpf());
        System.out.println(novoAgente.getTelefone());
        System.out.println(novoAgente.getEndereco());
        System.out.println(novoAgente.getUsuario());
        System.out.println(novoAgente.getSenha());
         */

        Statement stat = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;

        try {

            String sqlFunc = "INSERT INTO funcionario(nome,sexo," +
                    "datanascimento,cpf,endereco," +
                    "telefone) VALUES(?,?,to_date(?,'DD/MM/YYYY'),?,?,?) ";

            String sqlFunCon = "select * from funcionario where cpf = " + novoAgente.getCpf();

            String sqlAgen = "INSERT INTO agente (id_seq_agente,usuario,senha)" +
                    "VALUES(?,?,?);";

            //INSERINDO EM FUNCIONARIO....
            pst = connection.prepareStatement(sqlFunc);
            pst.setString(1, novoAgente.getNome());
            pst.setString(2, novoAgente.getSexo());
            pst.setString(3, novoAgente.getDatanascimento());
            pst.setLong(4, novoAgente.getCpf());
            pst.setString(5, novoAgente.getEndereco());
            pst.setLong(6, novoAgente.getTelefone());

            //insere em funcionario...
            pst.executeUpdate();
            pst.close();

            //Abrir consulta
            stat = connection.createStatement();
            rs = stat.executeQuery(sqlFunCon);

            //posicionar consulta
            rs.next();

            //INSERINDO EM AGENTE
            pst2 = connection.prepareStatement(sqlAgen);

            pst2.setInt(1, rs.getInt("id_seq_funcionario"));
            pst2.setString(2, novoAgente.getUsuario());
            pst2.setString(3, novoAgente.getSenha());

            //inserindo agente....
            pst2.executeUpdate();
            pst2.close();
            //fechando consulta...
            rs.close();
            stat.close();


        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }

    public Funcionario selectAgente(int id_seq_funcionario) throws RemoteException {

        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM funcionario INNER JOIN agente ON funcionario.id_seq_funcionario=agente.id_seq_agente " +
                "WHERE id_seq_funcionario=" + id_seq_funcionario;
        Funcionario fun = new Funcionario();

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            rs.next();
            fun.setIdSeqFuncionario(rs.getInt("id_seq_funcionario"));
            fun.setNome(rs.getString("nome"));
            fun.setSexo(rs.getString("sexo"));
            fun.setDatanascimento(rs.getString("datanascimento"));
            fun.setCpf(rs.getLong("cpf"));
            fun.setEndereco(rs.getString("endereco"));
            fun.setTelefone(rs.getLong("telefone"));
            fun.setUsuario(rs.getString("usuario"));
            fun.setSenha(rs.getString("senha"));

            rs.close();
            st.close();

            return fun;

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
        return null;
    }

    public void deleteAgente(int id_seq_funcionario) throws RemoteException {
        Statement st = null;

        String sql = "DELETE FROM agente WHERE id_seq_agente=" + id_seq_funcionario;
        String sql2 = "DELETE FROM funcionario WHERE id_seq_funcionario=" + id_seq_funcionario;

        try {

            st = connection.createStatement();
            //System.out.println("executando: " + sql);
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
            st.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }


    }

    public void updateAgente(int id_seq_funcionario, Funcionario upFun) throws RemoteException {

        PreparedStatement pst = null;
        PreparedStatement pst2 = null;

        String sql = "UPDATE agente" +
                " set usuario = ?,senha = ?" +
                " WHERE id_seq_agente=" + id_seq_funcionario;

        String sql2 = "UPDATE funcionario" +
                " set nome = ?,sexo = ?," +
                "datanascimento = to_date(?,'DD/MM/YYYY'),cpf = ?,endereco = ?," +
                "telefone = ? WHERE id_seq_funcionario=" + id_seq_funcionario;

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, upFun.getUsuario());
            pst.setString(2, upFun.getSenha());

            pst2 = connection.prepareStatement(sql2);

            pst2.setString(1, upFun.getNome());
            pst2.setString(2, upFun.getSexo());
            pst2.setString(3, upFun.getDatanascimento());
            pst2.setLong(4, upFun.getCpf());
            pst2.setString(5, upFun.getEndereco());
            pst2.setLong(6, upFun.getTelefone());

            pst.executeUpdate();
            pst.close();
            pst2.executeUpdate();
            pst2.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }
    ///////////////////////////////////////////////////////////////////////////

    /////////////////////FUNÇOES DE MOTORISTA//////////////////////////////////
    public void insertMotorista(Funcionario novoMotorista) throws RemoteException, ParseException {
        /*
        System.out.println(novoMotorista.getNome());
        System.out.println(novoMotorista.getSexo());
        System.out.println(novoMotorista.getDatanascimento());
        System.out.println(novoMotorista.getCpf());
        System.out.println(novoMotorista.getTelefone());
        System.out.println(novoMotorista.getEndereco());
        System.out.println(novoMotorista.getCnh());
         */

        Statement stat = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;

        try {


            String sqlFunc = "INSERT INTO funcionario(nome,sexo," +
                    "datanascimento,cpf,endereco," +
                    "telefone) VALUES(?,?,to_date(?,'DD/MM/YYYY'),?,?,?) ";

            String sqlFunCon = "select * from funcionario where cpf = " + novoMotorista.getCpf();

            String sqlMot = "INSERT INTO motorista (id_seq_motorista,cnh)" +
                    "VALUES(?,?);";

            //INSERINDO EM FUNCIONARIO....
            pst = connection.prepareStatement(sqlFunc);
            pst.setString(1, novoMotorista.getNome());
            pst.setString(2, novoMotorista.getSexo());
            pst.setString(3, novoMotorista.getDatanascimento());
            pst.setLong(4, novoMotorista.getCpf());
            pst.setString(5, novoMotorista.getEndereco());
            pst.setLong(6, novoMotorista.getTelefone());

            //insere em funcionario...
            pst.executeUpdate();
            pst.close();

            //CAPTURANDO id_seq_funcionario EM FUNCIONARIO PARA RELACIONAR COM MOTORISTA
            Funcionario fun = new Funcionario();

            stat = connection.createStatement();
            rs = stat.executeQuery(sqlFunCon);

            //posicionando consulta
            rs.next();

            //INSERINDO EM MOTORISTA
            pst2 = connection.prepareStatement(sqlMot);

            pst2.setInt(1, rs.getInt("id_seq_funcionario"));
            pst2.setLong(2, novoMotorista.getCnh());

            //inserindo agente....
            pst2.executeUpdate();
            pst2.close();
            //fechando consulta...
            rs.close();
            stat.close();


        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }

    public Funcionario selectMotorista(int id_seq_funcionario) throws RemoteException {

        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM funcionario INNER JOIN motorista ON funcionario.id_seq_funcionario=motorista.id_seq_motorista " +
                "WHERE id_seq_funcionario=" + id_seq_funcionario;
        Funcionario fun = new Funcionario();

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            rs.next();
            fun.setIdSeqFuncionario(rs.getInt("id_seq_funcionario"));
            fun.setNome(rs.getString("nome"));
            fun.setSexo(rs.getString("sexo"));
            fun.setDatanascimento(rs.getString("datanascimento"));
            fun.setCpf(rs.getLong("cpf"));
            fun.setEndereco(rs.getString("endereco"));
            fun.setTelefone(rs.getLong("telefone"));
            fun.setCnh(rs.getLong("cnh"));

            rs.close();
            st.close();

            return fun;

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
        return null;
    }

    public void deleteMotorista(int id_seq_funcionario) throws RemoteException {
        Statement st = null;

        String sql = "DELETE FROM motorista WHERE id_seq_motorista=" + id_seq_funcionario;
        String sql2 = "DELETE FROM funcionario WHERE id_seq_funcionario=" + id_seq_funcionario;

        try {

            st = connection.createStatement();
            //System.out.println("executando: " + sql);
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
            st.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }


    }

    public void updateMotorista(int id_seq_funcionario, Funcionario upFun) throws RemoteException {

        PreparedStatement pst = null;
        PreparedStatement pst2 = null;

        String sql = "UPDATE motorista" +
                " set cnh = ?" +
                " WHERE id_seq_motorista=" + id_seq_funcionario;

        String sql2 = "UPDATE funcionario" +
                " set nome = ?,sexo = ?," +
                "datanascimento = to_date(?,'DD/MM/YYYY'),cpf = ?,endereco = ?," +
                "telefone = ? WHERE id_seq_funcionario=" + id_seq_funcionario;

        try {

            pst = connection.prepareStatement(sql);

            pst.setLong(1, upFun.getCnh());


            pst2 = connection.prepareStatement(sql2);

            pst2.setString(1, upFun.getNome());
            pst2.setString(2, upFun.getSexo());
            pst2.setString(3, upFun.getDatanascimento());
            pst2.setLong(4, upFun.getCpf());
            pst2.setString(5, upFun.getEndereco());
            pst2.setLong(6, upFun.getTelefone());

            pst.executeUpdate();
            pst.close();
            pst2.executeUpdate();
            pst2.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }
    /////////////////////////////////////////////////////////////////

    //////////////////////FUNÇÕES DE CARRO//////////////////////////////////////
    public void insertCarro(Carro car) throws RemoteException {
        try {
            PreparedStatement pst = null;
            //ResultSet rs = null;

            String sql = "INSERT INTO carro(placa,chassis,arcondicionado,capacidade)" +
                    "VALUES(?,?,?,?) ";

            pst = connection.prepareStatement(sql);

            pst.setString(1, car.getPlaca());
            pst.setString(2, car.getChassis());
            pst.setString(3, car.getArCondicionado());
            pst.setInt(4, car.getCapacidade());

            pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }

    public Carro selectCarro(int id_seq_carro) throws RemoteException {

        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM carro " +
                "WHERE id_seq_carro=" + id_seq_carro;

        Carro car = new Carro();

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            rs.next();
            car.setId_seq_carro(rs.getInt("id_seq_carro"));
            car.setPlaca(rs.getString("placa"));
            car.setChassis(rs.getString("chassis"));
            car.setArCondicionado(rs.getString("arcondicionado"));
            car.setCapacidade(rs.getInt("capacidade"));

            rs.close();
            st.close();

            return car;

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
        return null;
    }

    public void deleteCarro(int id_seq_carro) throws RemoteException {
        Statement st = null;

        String sql = "DELETE FROM carro WHERE id_seq_carro=" + id_seq_carro;

        try {
            st = connection.createStatement();
            //System.out.println("executando: " + sql);
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }


    }

    public void updateCarro(int id_seq_carro, Carro upCar) throws RemoteException {

        PreparedStatement pst = null;

        String sql = "UPDATE carro" +
                " set placa = ?,chassis = ?,arcondicionado = ?,capacidade=?" +
                " WHERE id_seq_carro=" + id_seq_carro;


        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, upCar.getPlaca());
            pst.setString(2, upCar.getChassis());
            pst.setString(3, upCar.getArCondicionado());
            pst.setInt(4, upCar.getCapacidade());

            pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
        }
    }
    ////////////////////////////////////////////////////////////////////////////

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
