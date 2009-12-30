package administracao.gui.funcionario;

import administracao.database.DataBaseManagerImpl;
import administracao.funcionario.Funcionario;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import util.Auxiliares;
import util.TableModel;


public class JanelaAdministraFuncionario extends JFrame {

    private JComboBox tipoFuncionario = new JComboBox(Auxiliares.TIPO_FUNCIONARIO);
    private TableModel tableModel;
    private JTextField dadoBusca = new JTextField(40);
    private JTextField filtroBusca = new JTextField(40);
    private JTable resultTable;
    private JButton cadastraNovo = new JButton("Cadastrar Novo");
    private JButton retorna = new JButton("Retornar Todos");   

    private boolean agenteBool = false;
    private boolean motoristaBool = false;
    
    public final DataBaseManagerImpl dbm;

    public JanelaAdministraFuncionario(final DataBaseManagerImpl dbm){

        super("Administrador de Funcionário");

        setBounds(390,220,500,400);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;

        //-----

        try {
            // cria o TableModel            
            tableModel = new TableModel(dbm, "SELECT * FROM funcionario");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        tipoFuncionario.setBounds(5,10,100,40);
        add(tipoFuncionario);

        filtroBusca.setBounds(110,10,175,40);
        filtroBusca.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Filtro *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(filtroBusca);

        dadoBusca.setBounds(290, 10, 200, 40);
        dadoBusca.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Dado", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(dadoBusca);

        JButton submitButton = new JButton("Buscar");
        submitButton.setBounds(5,55,300,30);
        add(submitButton);

        retorna.setBounds(310,55,180,30);
        retorna.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        try {
                            if(tipoFuncionario.getSelectedIndex() == 1){
                                tableModel.setQuery(
                                        "SELECT * FROM funcionario INNER JOIN agente"+
                                        " ON funcionario.id_seq_funcionario = agente.id_seq_agente");

                                agenteBool = true;
                                motoristaBool = false;
                            }
                            else if(tipoFuncionario.getSelectedIndex() == 2){
                                tableModel.setQuery(
                                        "SELECT * FROM funcionario INNER JOIN motorista"+
                                        " ON funcionario.id_seq_funcionario = motorista.id_seq_motorista");

                                agenteBool = false;
                                motoristaBool = true;
                            }
                            else{
                                tableModel.setQuery("SELECT * FROM funcionario");

                                agenteBool = false;
                                motoristaBool = false;
                            }
                        } catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Erro de Database",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
         );
        add(retorna);

        resultTable = new JTable(tableModel);
        JScrollPane resultScroll = new JScrollPane(resultTable);

        resultScroll.setBounds(5,90,485,230);
        add(resultScroll);

        cadastraNovo.setBounds(5,330,485,30);
        cadastraNovo.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {

                        if(tipoFuncionario.getSelectedIndex() == 1){
                            new CadastraNovoAgente(dbm,0);
                        }
                        else if (tipoFuncionario.getSelectedIndex() == 2){
                            new CadastraNovoMotorista(dbm,0);
                        }
                        else{
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Escolha o tipo de funcionário",
                                    "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }

                    }
                }
         );
        add(cadastraNovo);

        // cria evento ouvinte para submitButton
        submitButton.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {

                        // realiza uma nova consulta
                        try {
                            if(tipoFuncionario.getSelectedIndex() == 1){
                                tableModel.setQuery(
                                        "SELECT * FROM funcionario INNER JOIN agente"+
                                        " ON funcionario.id_seq_funcionario = agente.id_seq_agente"+
                                        " WHERE "+ filtroBusca.getText() +
                                        " LIKE '%" + dadoBusca.getText() + "%' ORDER BY nome");

                                agenteBool = true;
                                motoristaBool = false;

                            }
                            else if(tipoFuncionario.getSelectedIndex() == 2){
                                tableModel.setQuery(
                                        "SELECT * FROM funcionario INNER JOIN motorista"+
                                        " ON funcionario.id_seq_funcionario = motorista.id_seq_motorista"+
                                        " WHERE "+ filtroBusca.getText() +
                                        " LIKE '%" + dadoBusca.getText() + "%' ORDER BY nome");

                                agenteBool = false;
                                motoristaBool = true;

                            }
                            else{
                                tableModel.setQuery(
                                        "SELECT * FROM funcionario WHERE "+ filtroBusca.getText() +
                                        " LIKE '%" + dadoBusca.getText() + "%' ORDER BY nome");

                                agenteBool = false;
                                motoristaBool = false;
                            } // fim do try
                        }catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Database error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

        //evento de clicar no jtable
        resultTable.addMouseListener(new MouseClickedHandler());

        addWindowListener(
                new WindowAdapter() {
                    // desconecta-se do banco de dados e sai quando a janela for fechada

                    @Override
                    public void windowClosed(WindowEvent event) {
                        dispose();
                    }
                }
        );

         
    }

    private class MouseClickedHandler extends MouseAdapter {

        //tem 2 metodos essa classe interna,e um deles chama um auxiliar mais abaixo
        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() > 1) {
                apertarNoJTable();
            }
        }

        public void apertarNoJTable() {
            int select = resultTable.getSelectedRow();
            if (select >= 0) {

                try {
                     //lugar especifico onde clicou
                    Integer clicado = new Integer(resultTable.getValueAt(select, 0).toString());

                    if (tipoFuncionario.getSelectedIndex() == 1 && agenteBool==true) {
                        //quando buscar esse sera preenchido
                        Funcionario fun = new Funcionario();
                        //efetua a busca e preenche
                        fun = dbm.selectAgente(clicado);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        CadastraNovoAgente cna = inserirAgenteEmObjetos(fun);
                    }

                    else if (tipoFuncionario.getSelectedIndex() == 2 && motoristaBool==true) {
                        //quando buscar esse sera preenchido
                        Funcionario fun = new Funcionario();
                        //efetua a busca e preenche
                        fun = dbm.selectMotorista(clicado);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        CadastraNovoMotorista cnm = inserirMotoristaEmObjetos(fun);
                    }

                    else {
                        JOptionPane.showMessageDialog(
                                    null,
                                    "Para realizar a consulta escolha o tipo de funcionario "+
                                    "desejado.\n Em sequida retorne todos.",
                                    "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE
                        );
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

        }

        public CadastraNovoAgente inserirAgenteEmObjetos(Funcionario fun) throws ParseException {

            CadastraNovoAgente cna = new CadastraNovoAgente(dbm, 1);

                cna.identificadorIdSeq.setText(String.valueOf(fun.getIdSeqFuncionario()));

                cna.nomeAgente.setText(fun.getNome());

                if(fun.getSexo().indexOf("M") != -1)
                    cna.masculinoRB.setSelected(true);
                else
                    cna.femininoRB.setSelected(true);


                     //Recebe a data do BD da forma yyyy-mm-dd
                     Date dt = new SimpleDateFormat("yyyy-mm-dd").parse(fun.getDatanascimento());        

                                            //passa a data formatada dd/MM/yyyy
                cna.data_nascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(dt));

                cna.cpfAgente.setText(String.valueOf(fun.getCpf()));

                cna.enderecoAgente.setText(fun.getEndereco());

                cna.telefoneAgente.setText(String.valueOf(fun.getTelefone()));

                cna.usuarioAgente.setText(fun.getUsuario());

                cna.senhaAgente.setText(fun.getSenha());
                cna.senhaAgente2.setText(fun.getSenha());

            return cna;

        }

        public CadastraNovoMotorista inserirMotoristaEmObjetos(Funcionario fun) throws ParseException {

            CadastraNovoMotorista cnm = new CadastraNovoMotorista(dbm, 1);

                cnm.identificadorIdSeq.setText(String.valueOf(fun.getIdSeqFuncionario()));

                cnm.nomeMotorista.setText(fun.getNome());

                if(fun.getSexo().indexOf("M") != -1)
                    cnm.masculinoRB.setSelected(true);
                else
                    cnm.femininoRB.setSelected(true);

                    //Recebe a data do BD da forma yyyy-mm-dd
                    Date dt = new SimpleDateFormat("yyyy-mm-dd").parse(fun.getDatanascimento());

                cnm.data_nascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(dt));

                cnm.cpfMotorista.setText(String.valueOf(fun.getCpf()));

                cnm.enderecoMotorista.setText(fun.getEndereco());

                cnm.telefoneMotorista.setText(String.valueOf(fun.getTelefone()));

                cnm.cnhMotorista.setText(String.valueOf(fun.getCnh()));

            return cnm; 

        }
    }

}


