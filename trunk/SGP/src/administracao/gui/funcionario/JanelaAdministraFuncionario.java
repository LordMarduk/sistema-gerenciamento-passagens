package administracao.gui.funcionario;

import administracao.database.DataBaseManagerImpl;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import util.Auxiliares;
import util.JNumericField;


public class JanelaAdministraFuncionario extends JFrame {

    private JComboBox tipoFuncionario = new JComboBox(Auxiliares.TIPO_FUNCIONARIO);
    private FuncionarioTableModel tableModel;
    private JTextField nomeAgenteBusca = new JTextField(40);
    private JTextField cpfAgenteBusca = new JNumericField(11);
    private JTable resultTable;   
    private JButton cadastraNovo = new JButton("Cadastrar Novo");
    private JButton retorna = new JButton("Retornar");

    public final DataBaseManagerImpl dbm;

    public JanelaAdministraFuncionario(final DataBaseManagerImpl dbm){

        super("Administrador de Funcionário");

        setBounds(390,220,500,400);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;

        //-----

        try {
            // cria o TableModel
            tableModel = new FuncionarioTableModel(dbm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        resultTable = new JTable(tableModel);

        tipoFuncionario.setBounds(5,5,100,40);
        add(tipoFuncionario);

        nomeAgenteBusca.setBounds(110,5,200,40);
        nomeAgenteBusca.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Nome *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(nomeAgenteBusca);

        cpfAgenteBusca.setBounds(315, 5, 150, 40);
        cpfAgenteBusca.setBorder(
                BorderFactory.createTitledBorder(
                    null, "CPF *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(cpfAgenteBusca);

        JButton submitButton = new JButton("Buscar");
        submitButton.setBounds(5,45,300,30);
        add(submitButton);

        retorna.setBounds(310,45,170,30);
        add(retorna);

        resultTable.setBounds(5,80,475,230);
        add(resultTable);

        cadastraNovo.setBounds(5,320,475,30);
        add(cadastraNovo);

        // cria evento ouvinte para submitButton
        submitButton.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {

                        // realiza uma nova consulta
                        try {
                            if(tipoFuncionario.getSelectedIndex() == 1)
                                tableModel.setQuery("SELECT * FROM funcionario WHERE nome LIKE '%" + nomeAgenteBusca.getText() + "%'");
                            else if(tipoFuncionario.getSelectedIndex() == 2)
                                tableModel.setQuery("SELECT * FROM funcionario WHERE cpf LIKE '%" + nomeAgenteBusca.getText() + "%'");
                            else
                                tableModel.setQuery("SELECT * FROM funcionario");
                            } // fim do try
                        catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Database error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

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
                });
    }

}


