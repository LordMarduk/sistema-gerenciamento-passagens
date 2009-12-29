package rodoviaria.gui.cliente;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import util.DataBaseManager;
import util.JNumericField;
import util.QueryManager;
import util.TableModelCliente;

public class GerenciamentoDeClientes extends JFrame {

    public final DataBaseManager dbm;
    public final QueryManager qm;

    private JPanel filtersPanel = new JPanel();
    private JPanel queryResultPanel = new JPanel();

    private TableModelCliente tmc;
    private JTable queryResultTable;

    private JTextField nome = new JTextField();
    private JTextField endereco = new JTextField();
    private JTextField cpf = new JNumericField(11);

    private String est[] = {"Indiferente", "Estudante", "Não-Estudante"};
    private String sex[] = {"Indiferente", "Homem", "Mulher"};

    private String query = "SELECT * FROM cliente ORDER BY nome";

    private JComboBox estudante = new JComboBox(est);
    private JComboBox sexo = new JComboBox(sex);

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar Novo");
    private JButton sair = new JButton("Sair");

    public GerenciamentoDeClientes(final DataBaseManager dbm, final QueryManager qm) {

        super("Clientes");
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setBounds(150, 10, 1000, 750);
        setLayout(null);

        this.dbm = dbm;
        this.qm = qm;

        filtersPanel.setBounds(5, 5, 985, 150);
        filtersPanel.setBorder( new EtchedBorder() );
        filtersPanel.setLayout(null);

        queryResultPanel.setBounds(5, 155, 985, 565);
        queryResultPanel.setBorder( new EtchedBorder() );
        queryResultPanel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(100, 5, 50, 20);
        filtersPanel.add(nomeLabel);
        nome.setBounds(100, 25, 300, 25);
        filtersPanel.add(nome);

        JLabel enderecoLabel = new JLabel("Endereço");
        enderecoLabel.setBounds(100, 50, 100, 20);
        filtersPanel.add(enderecoLabel);
        endereco.setBounds(100, 70, 300, 25);
        filtersPanel.add(endereco);

        JLabel cpfLabel = new JLabel("CPF");
        cpfLabel.setBounds(100, 95, 50, 20);
        filtersPanel.add(cpfLabel);
        cpf.setBounds(100, 115, 300, 25);
        filtersPanel.add(cpf);

        JLabel estudanteLabel = new JLabel("Estudante");
        estudanteLabel.setBounds(450, 20, 100, 20);
        filtersPanel.add(estudanteLabel);
        estudante.setBounds(450, 40, 200, 25);
        filtersPanel.add(estudante);

        JLabel sexoLabel = new JLabel("Sexo");
        sexoLabel.setBounds(450, 80, 100, 20);
        filtersPanel.add(sexoLabel);
        sexo.setBounds(450, 100, 200, 25);
        filtersPanel.add(sexo);

        atualizar.setBounds(700, 20, 200, 30);
        cadastrar.setBounds(700, 60, 200, 30);
        sair.setBounds(700, 100, 200, 30);

        atualizar.addActionListener( new AtualizarHandler() );

        cadastrar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    NovoCliente nc = new NovoCliente(dbm);
                    nc.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
                }
            }
        );

        sair.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            }
        );

        filtersPanel.add(atualizar);
        filtersPanel.add(cadastrar);
        filtersPanel.add(sair);

        tmc = new TableModelCliente(qm, this.query);
        queryResultTable = new JTable(tmc);
        JScrollPane tableScroolPane = new JScrollPane(queryResultTable);
        tableScroolPane.setBounds(5, 5, 975, 555);

        queryResultTable.addMouseListener( new TableRowsEventHandler() );
        queryResultTable.setRowSelectionAllowed(true);

        queryResultPanel.add(tableScroolPane);

        add(filtersPanel);
        add(queryResultPanel);

        filtersPanel.repaint();
        queryResultPanel.repaint();
        repaint();

    }

    public class TableRowsEventHandler implements MouseListener{

        public void mouseClicked(MouseEvent e) {
            int row = queryResultTable.getSelectedRow();
        }

        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }



    }

    public class AtualizarHandler implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            boolean changed = true;
            String newQuery = "SELECT * FROM cliente";

            if( ( nome.getText() == null )&&( endereco.getText() == null )&&
                    ( cpf.getText() == null )&&( estudante.getSelectedIndex() == 0 )&&
                    ( sexo.getSelectedIndex() == 0 ) )
                changed = false;

            if(changed){

                newQuery += " WHERE ";
                boolean already = false;

                if(nome.getText() != null){
                    already = true;
                    newQuery += "(nome LIKE '%" + nome.getText() + "%')";
                }

                if(endereco.getText() != null){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(endereco LIKE '%" + endereco.getText() + "%')";
                }

                if(cpf.getText() != null){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(cpf LIKE '%" + cpf.getText() + "%')";
                }

                if(estudante.getSelectedIndex() != 0){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(e_estudante = " + (estudante.getSelectedIndex() == 1 ? "true" : "false") + ")";
                }

                if(sexo.getSelectedIndex() != 0){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(sexo = " + (sexo.getSelectedIndex() == 1 ? "'M'" : "'F'") + ")";
                }

                query = newQuery;
                tmc.setQuery(query);
                repaint();

            }

        }

    }

}