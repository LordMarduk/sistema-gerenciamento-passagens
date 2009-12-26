package rodoviaria.gui;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GerenciamentoDeClientes extends JFrame {

    private final DataBaseManager dbm;

    private JPanel filtersPanel = new JPanel();
    private JPanel queryResultPanel = new JPanel();
    
    private JTable queryResultTable = new JTable(  );

    private JTextField nome = new JTextField();
    private JTextField endereco = new JTextField();
    private JTextField cpf = new JNumericField(11);

    private String est[] = {"Indiferente", "Estudante", "Não-Estudante"};
    private String sex[] = {"Indiferente", "Homem", "Mulher"};

    private JComboBox estudante = new JComboBox(est);
    private JComboBox sexo = new JComboBox(sex);

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar Novo");
    private JButton sair = new JButton("Sair");

    public GerenciamentoDeClientes(final DataBaseManager dbm) {

        super("Clientes");
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setBounds(150, 10, 1000, 750);
        setLayout(null);

        this.dbm = dbm;

        filtersPanel.setBounds(5, 5, 985, 150);
        filtersPanel.setBorder( new EtchedBorder() );
        filtersPanel.setLayout(null);

        queryResultPanel.setBounds(5, 155, 985, 565);
        queryResultPanel.setBorder( new EtchedBorder() );
        queryResultPanel.setLayout(null);

        queryResultTable.setBounds(5, 5, 975, 555);
        queryResultTable.add( new JScrollPane() );
        
        queryResultPanel.add(queryResultTable);

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

        atualizar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // NovoCliente nc = new NovoCliente(dbm);
                }
            }
        );

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

        add(filtersPanel);
        add(queryResultPanel);

        repaint();

    }
}
