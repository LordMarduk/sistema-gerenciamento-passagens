package administracao.gui.viagens;

import administracao.database.DataBaseManagerImpl;
import administracao.viagens.TipoDeViagem;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import util.Auxiliares;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GerenciaViagens extends JFrame {

    private TableModel tableModel;
    private JTextField filtroTF;
    private JTextField consultaTF;
    private JButton retornarBuscaButton;
    private JButton pesquisarButton;
    private JTable resultTable;
    public final DataBaseManagerImpl dbm;
    private JComboBox flagViagens;
    FlowLayout layout;
    BorderLayout border;
    JPanel painel;
    JPanel painel2;
    JPanel painel3;

    public GerenciaViagens(final DataBaseManagerImpl dbm) {
        super("Viagens");

        setLocationRelativeTo(this);
        //setLayout(null);
        setBounds(390, 220, 500, 400);
        setVisible(true);

        layout = new FlowLayout();
        border = new BorderLayout();
        painel = new JPanel();
        painel2 = new JPanel();
        painel3 = new JPanel();
        setLayout(border);

        layout.setAlignment(FlowLayout.LEFT);
        painel.setLayout(layout);

        this.dbm = dbm;


        try {
            // cria o TableModel
            tableModel = new TableModel(dbm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        flagViagens = new JComboBox(Auxiliares.FLAG_VIAGENS);
        flagViagens.setBounds(5, 5, 120, 50);

        filtroTF = new JTextField("", 15);
        filtroTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Filtro", 0, 0, new Font("Tahoma", 0, 10)));

        consultaTF = new JTextField("", 30);
        consultaTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Consulta", 0, 0, new Font("Tahoma", 0, 10)));


        // configura o JButton para enviar consulta
        pesquisarButton = new JButton("Busca Tipos De Viagem");
        pesquisarButton.setSize(30,30);
        retornarBuscaButton = new JButton("Retornar busca");
        retornarBuscaButton.setSize(30,30);
       



        painel.add(flagViagens);
        painel.add(filtroTF);
        painel.add(consultaTF);
        //painel.add(pesquisarButton);
        //painel.add(retornarBuscaButton);

        painel2.add(pesquisarButton);
        painel2.add(retornarBuscaButton);

        add(painel, BorderLayout.NORTH);
        add(painel2, BorderLayout.CENTER);
       

        // cria o delegado JTable para tableModel
        resultTable = new JTable(tableModel);
        //add(resultTable);

        //botao de cadastro:
        JButton cadastroButton = new JButton("Cadastrar Novo");
        cadastroButton.setSize(300, 50);
        //jPanel3.add(cadastroButton);
        //add(cadastroButton);
        //Box box2 = Box.createHorizontalBox();
        //box2.add(Box.createHorizontalGlue());
        //box2.add(cadastroButton);

        // posiciona os componentes GUI no painel de conte�do
        //add(box, BorderLayout.NORTH);
        //add(jPanel1,BorderLayout.NORTH);
        add(new JScrollPane(resultTable), BorderLayout.SOUTH);
        //add(jPanel3, BorderLayout.SOUTH);
        //add(box2, BorderLayout.SOUTH);

        // cria evento ouvinte para cadastroButton
        cadastroButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {

                        try {
                            CadastraTipoDeViagem ctdv = new CadastraTipoDeViagem(0, dbm);
                            //ctdv.setLocationRelativeTo();
                            ctdv.setVisible(true);
                            ctdv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                        } // fim do try
                        catch (Exception s) {
                            JOptionPane.showMessageDialog(null,
                                    s.getMessage(), "erro",
                                    JOptionPane.ERROR_MESSAGE);
                        } // fim do catch externo
                        } // fim do m�todo actionPerformed
                    } // fim da classe ActionListener interna
                ); // fim da chamada para addActionListener


        // cria evento ouvinte para pesquisarButton
        pesquisarButton.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {
                        // realiza uma nova consulta
                        try {
                            tableModel.setQuery("SELECT * FROM tipo_de_viagem WHERE dias_da_semana LIKE '%" + /*queryArea.getText() +*/ "%'");

                        } // fim do try
                        catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Database error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        retornarBuscaButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        try {
                            tableModel.setQuery("SELECT * FROM tipo_de_viagem");
                        } catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Erro de Database",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        //evento de clicar no jtable
        resultTable.addMouseListener(new MouseClickedHandler());

        addWindowListener(
                new WindowAdapter() {
                    // desconecta-se do banco de dados e sai quando a janela for fechada

                    @Override
                    public void windowClosed(WindowEvent event) {
                        dispose();
                    }
                });
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
                    //TipoDeViagemTableModel tdvtm = new TipoDeViagemTableModel();

                    //objeto que irá buscar o que foi clicado

                    //quando buscar esse sera preenchido
                    TipoDeViagem tdv = new TipoDeViagem();
                    //efetua a busca e preenche
                    tdv = dbm.selectTipoDeViagem(clicado);
                    //janela que exibira os dados    mapeamento: objeto -> GUI
                    CadastraTipoDeViagem ctdv = inserirObjetoEmGui(tdv);
                    ctdv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    ctdv.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

        }
    }

    public CadastraTipoDeViagem inserirObjetoEmGui(TipoDeViagem tdv) {
        CadastraTipoDeViagem ctdv = new CadastraTipoDeViagem(1, dbm);

        ctdv.identificadorSequencialTF.setText(String.valueOf(tdv.getIdSeqTdv()));
        ctdv.valorViagemTF.setText(String.valueOf(tdv.getValorViagem()));
        ctdv.horaDeSaidaTF.setText(tdv.getHoraPrevSaida());
        //ctdv.filtroTF.setText(tdv.getHoraPrevChegada());

        //tratar dos check boxes(dias da semana):
        if (tdv.getDiasDaSemana().indexOf("segunda") != -1) {
            ctdv.segundaCB.setSelected(true);
        }
        if (tdv.getDiasDaSemana().indexOf("terca") != -1) {
            ctdv.tercaCB.setSelected(true);
        }
        if (tdv.getDiasDaSemana().indexOf("quarta") != -1) {
            ctdv.quartaCB.setSelected(true);
        }
        if (tdv.getDiasDaSemana().indexOf("quinta") != -1) {
            ctdv.quintaCB.setSelected(true);
        }
        if (tdv.getDiasDaSemana().indexOf("sexta") != -1) {
            ctdv.sextaCB.setSelected(true);
        }
        if (tdv.getDiasDaSemana().indexOf("sabado") != -1) {
            ctdv.sabadoCB.setSelected(true);
        }
        if (tdv.getDiasDaSemana().indexOf("domingo") != -1) {
            ctdv.domingoCB.setSelected(true);
        }

        ctdv.idSeqRodovPartida.setText(String.valueOf(tdv.getIdSeqRodovPartida()));
        ctdv.idSeqRodovChegada.setText(String.valueOf(tdv.getIdSeqRodovChegada()));

        return ctdv;
    }
} // fim da classe DisplayQueryFilme

