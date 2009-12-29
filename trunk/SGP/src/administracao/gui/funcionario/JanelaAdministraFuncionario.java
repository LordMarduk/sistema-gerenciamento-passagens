package administracao.gui.funcionario;

import administracao.database.DataBaseManagerImpl;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
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
import util.JNumericField;
import util.TableModel;


public class JanelaAdministraFuncionario extends JFrame {

    private JLabel tipoFuncionarioJLabel = new JLabel("Tipo Funcionário *");
    private JComboBox tipoFuncionario = new JComboBox(Auxiliares.TIPO_FUNCIONARIO);
    private TableModel tableModel;
    private JTextField dadoBusca = new JTextField(40);
    private JTextField filtroBusca = new JTextField(40);
    private JTable resultTable;
    private JButton cadastraNovo = new JButton("Cadastrar Novo");
    private JButton retorna = new JButton("Retornar Todos");   

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

        tipoFuncionarioJLabel.setBounds(5,0,100,20);
        add(tipoFuncionarioJLabel);

        tipoFuncionario.setBounds(5,15,100,35);
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
                            tableModel.setQuery("SELECT * FROM funcionario");
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
                            new CadastraNovoAgente(dbm);
                        }
                        else if (tipoFuncionario.getSelectedIndex() == 2){
                            new CadastraNovoMotorista(dbm);
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
                                        "SELECT * FROM funcionario "+
                                        "INNER JOIN agente ON funcionario.id_seq_funcionario = agente.id_seq_agente"+
                                        " WHERE "+ filtroBusca.getText() +
                                        " LIKE '%" + dadoBusca.getText() + "%'");
                            }
                            else if(tipoFuncionario.getSelectedIndex() == 2){
                                tableModel.setQuery(
                                        "SELECT * FROM funcionario "+
                                        "INNER JOIN agente ON funcionario.id_seq_funcionario = motorista.id_seq_agente"+
                                        " WHERE "+ filtroBusca.getText() +
                                        " LIKE '%" + dadoBusca.getText() + "%'");
                            }
                            else{
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Escolha o tipo de funcionário",
                                    "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE
                                );                                
                            } // fim do try
                        }catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Database error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );       

         
    }

}


