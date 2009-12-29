package administracao.gui.carro;

import administracao.database.DataBaseManagerImpl;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.TableModel;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JanelaAdministraCarro extends JFrame {

    private JLabel carroJLabel = new JLabel("CARRO");
    
    private JTextField filtroBusca = new JTextField(40);
    private JTextField dadoBusca = new JTextField(40);
    
    private TableModel tableModel;
    
    private JTable resultTable;

    private JButton retorna = new JButton("Retornar Todos");
    private JButton cadastraNovo = new JButton("Cadastrar Novo");

    public final DataBaseManagerImpl dbm;


    public JanelaAdministraCarro(final DataBaseManagerImpl dbm) {

        super("Administrador de Carro");

        setBounds(390,220,500,400);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;

        //-----

        try {
            // cria o TableModel
            tableModel = new TableModel(dbm, "SELECT * FROM carro");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        carroJLabel.setBounds(35,20,100,20);
        add(carroJLabel);

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

        resultTable = new JTable(tableModel);
        JScrollPane resultScroll = new JScrollPane(resultTable);

        resultScroll.setBounds(5,90,485,230);
        add(resultScroll);

        JButton submitButton = new JButton("Buscar");
        submitButton.setBounds(5,55,300,30);
        add(submitButton);

        retorna.setBounds(310,55,180,30);
        retorna.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        try {
                            tableModel.setQuery("SELECT * FROM carro");
                        } catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Erro de Database",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
         );
        add(retorna);    

        cadastraNovo.setBounds(5,330,485,30);
        cadastraNovo.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                            new CadastraNovoCarro(dbm);
                    }
                }
         );
        add(cadastraNovo);

        // cria evento ouvinte para submitButton
        submitButton.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {
                        try {
                            tableModel.setQuery(
                                    "SELECT * FROM carro "+
                                    "WHERE "+ filtroBusca.getText() +
                                    " LIKE '%" + dadoBusca.getText() + "%'");
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
