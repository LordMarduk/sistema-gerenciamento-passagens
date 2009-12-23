package administracao.gui.tipo_de_viagem;

import administracao.database.DataBaseManagerImpl;
import administracao.tipo_de_viagem.TipoDeViagem;
import java.awt.BorderLayout;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JOptionPane;

public class GerenciaTipoDeViagem extends JFrame {

    private TipoDeViagemTableModel tableModel;
    private JTextArea queryArea;
    private JButton voltaButton;
    private JTable resultTable;
    public final DataBaseManagerImpl dbm;

    public GerenciaTipoDeViagem(final DataBaseManagerImpl dbm) {
        super("Tipos De Viagem");

        setLocationRelativeTo(this);
        setBounds(390,220,500,400);
        setVisible(true);

        //-----

        this.dbm = dbm;
        
        //-----

        try {            
            // cria o TableModel
            tableModel = new TipoDeViagemTableModel(dbm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        // configura JTextArea em que o usu�rio digita consultas
        queryArea = new JTextArea(3, 100);
        queryArea.setWrapStyleWord(true);
        queryArea.setLineWrap(true);

        //barra de rolagem
        JScrollPane scrollPane = new JScrollPane(queryArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // configura o JButton para enviar consulta
        JButton submitButton = new JButton("Busca Tipos De Viagem");
        voltaButton = new JButton("Retornar");
        //voltaButton.setEditable( false );

        // cria o Box para gerenciar o posicionamento da queryArea e do
        // submitButton na GUI
        Box box = Box.createHorizontalBox();
        box.add(scrollPane);
        box.add(submitButton);
        box.add(voltaButton);


        // cria o delegado JTable para tableModel
        resultTable = new JTable(tableModel);

        //botao de cadastro:
        JButton cadastroButton = new JButton("Cadastrar Novo");
        Box box2 = Box.createHorizontalBox();
        box2.add(cadastroButton);

        // posiciona os componentes GUI no painel de conte�do
        add(box, BorderLayout.NORTH);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);
        add(box2, BorderLayout.SOUTH);

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


        // cria evento ouvinte para submitButton
        submitButton.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {
                        // realiza uma nova consulta
                        try {
                            tableModel.setQuery("SELECT * FROM tipo_de_viagem WHERE dias_da_semana LIKE '%" + queryArea.getText() + "%'");
                            //voltaButton.setEditable( true );
                            } // fim do try
                        catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Database error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        voltaButton.addActionListener(
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
                    dbm.selectTipoDeViagem(clicado);
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
        ctdv.horaDeChegadaTF.setText(tdv.getHoraPrevChegada());

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

