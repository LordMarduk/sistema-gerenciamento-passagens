package administracao.gui.viagens;

import administracao.database.DataBaseManagerImpl;
import administracao.viagens.InstanciaDeViagem;
import administracao.viagens.TipoDeViagem;
import util.TableModel;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import util.Auxiliares;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.util.Date;

public class GerenciaViagens extends JFrame {

    private TableModel tableModel;
    private JTextField filtroTF;
    private JTextField consultaTF;
    private JButton retornarTodosButton;
    private JButton pesquisarButton;
    private JButton cadastroButton;
    private JTable resultTable;
    public final DataBaseManagerImpl dbm;
    private JComboBox flagViagens;

    public GerenciaViagens(final DataBaseManagerImpl dbm) {

        super("Viagens");

        //paineis para organizar componentes
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        //configura o layout para BoxLayout Vertical
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p4.setLayout(new BorderLayout());

        setLocation(300, 200);
        setSize(650, 400);
        setResizable(false);
        setVisible(true);

        this.dbm = dbm;

        try {
            // cria o TableModel
            //aqui deve ser ordenado pela data*************************************************
            tableModel = new TableModel(dbm, "select * from instancia_de_viagem ORDER BY data");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        flagViagens = new JComboBox(Auxiliares.FLAG_VIAGENS);
        flagViagens.setBackground(Color.GRAY);
        flagViagens.setFont(new Font("Verdana", 0, 15));

        filtroTF = new JTextField("", 15);
        filtroTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Filtro", 0, 0, new Font("Verdana", 0, 10)));

        consultaTF = new JTextField("", 30);
        consultaTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Consulta", 0, 0, new Font("Verdana", 0, 10)));

        pesquisarButton = new JButton("Buscar");
        pesquisarButton.setFont(new Font("Verdana", 0, 12));

        retornarTodosButton = new JButton("Retornar Todos");
        retornarTodosButton.setFont(new Font("Verdana", 0, 12));

        p2.add(flagViagens);
        p2.add(filtroTF);
        p2.add(consultaTF);

        p3.add(pesquisarButton);
        p3.add(retornarTodosButton);

        // cria o delegado JTable para tableModel
        resultTable = new JTable(tableModel);
        resultTable.setAutoCreateRowSorter(true);


        //botao de cadastro:
        cadastroButton = new JButton("Cadastrar Novo");
        cadastroButton.setFont(new Font("Verdana", 0, 14));
        p4.add(cadastroButton);

        p.add(p2);
        p.add(p3);
        JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
        p.add(separador);
        p.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        p.add(separador);
        p.add(p4);
        add(p);


        // cria evento ouvinte para cadastroButton
        cadastroButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {

                        try {
                            //se o flag tiver em instancia de viagem
                            if (flagViagens.getSelectedIndex() == 0) {
                                new CadastraInstanciaDeViagem(Auxiliares.CADASTRAR, dbm);
                            }
                            //se o flag tiver em tipo de viagem
                            if (flagViagens.getSelectedIndex() == 1) {
                                new CadastraTipoDeViagem(Auxiliares.CADASTRAR, dbm);
                            }

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
                            if (flagViagens.getSelectedIndex() == 0) {
                                //devo usar um order by pela data depois******************************************
                                tableModel.setQuery("SELECT * FROM instancia_de_viagem  WHERE " 
                                        + "CAST(" + filtroTF.getText() + " AS VARCHAR)"
                                        + " LIKE '%" + consultaTF.getText() +
                                        "%' ORDER BY data");
                            }
                            if (flagViagens.getSelectedIndex() == 1) {
                                tableModel.setQuery("SELECT * FROM tipo_de_viagem  WHERE " 
                                        + "CAST(" + filtroTF.getText() + " AS VARCHAR)"
                                        + " LIKE '%" + consultaTF.getText() +
                                        "%' ORDER BY id_seq_tdv");
                            }

                        } // fim do try
                        catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null,
                                    sqlException.getMessage(), "Database error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        retornarTodosButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (flagViagens.getSelectedIndex() == 0) {
                                //aqui devo ordenar pela data***********************************************
                                tableModel.setQuery("SELECT * FROM instancia_de_viagem ORDER BY data");
                            }

                            if (flagViagens.getSelectedIndex() == 1) {
                                tableModel.setQuery("SELECT * FROM tipo_de_viagem ORDER BY id_seq_tdv");
                            }

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
                    //Integer pegarIdSeqTdvNoJTable = new Integer(resultTable.getValueAt(select, 0).toString());
                    Integer pegarIdSeqTdvNoJTable = null;
                    String pegarDataNoJTable = null;

                    if (flagViagens.getSelectedIndex() == 0) {
                        //quando buscar esse sera preenchido
                        InstanciaDeViagem idv = new InstanciaDeViagem();

                        //pegar id seq tdv, está no indice 3 de acordo com a definicao do BD
                        //pegarIdSeqTdvNoJTable = new Integer(resultTable.getValueAt(select, 3).toString());

                        //aqui tambem preciso da data: que está no indice 0 para tipo de viagem
                        pegarDataNoJTable = new String(resultTable.getValueAt(select,0).toString());
                        //pegar id seq tdv, está no indice 3 de acordo com a definicao do BD
                        pegarIdSeqTdvNoJTable = new Integer(resultTable.getValueAt(select, 3).toString());

                        //efetua a busca e preenche
                        idv = dbm.selectInstanciaDeViagem(pegarIdSeqTdvNoJTable, pegarDataNoJTable);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        CadastraInstanciaDeViagem cidv = inserirInstanciaDeViagemEmGui(idv);
                    }

                    if (flagViagens.getSelectedIndex() == 1) {
                        //quando buscar esse sera preenchido
                        TipoDeViagem tdv = new TipoDeViagem();

                        //pegar id seq tdv, está no indice 0 de acordo com a definicao do BD
                        pegarIdSeqTdvNoJTable = new Integer(resultTable.getValueAt(select, 0).toString());

                        //efetua a busca e preenche
                        tdv = dbm.selectTipoDeViagem(pegarIdSeqTdvNoJTable);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        CadastraTipoDeViagem ctdv = inserirTipoDeViagemEmGui(tdv);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

        }
    }

    public CadastraTipoDeViagem inserirTipoDeViagemEmGui(TipoDeViagem tdv) {
        CadastraTipoDeViagem ctdv = new CadastraTipoDeViagem(Auxiliares.ALTERAR, dbm);

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

    public CadastraInstanciaDeViagem inserirInstanciaDeViagemEmGui(InstanciaDeViagem idv)
    throws ParseException {
        CadastraInstanciaDeViagem cidv = new CadastraInstanciaDeViagem(Auxiliares.ALTERAR, dbm);

        cidv.idSeqTdvTF.setText(String.valueOf(idv.getIdSeqTdv()));

        cidv.numVagasDisponiveisTF.setText(String.valueOf(idv.getNumVagasDisponiveis()));

        cidv.horaDeSaidaTF.setText(idv.getHoraRealSaida());
        cidv.horaDeChegadaTF.setText(idv.getHoraRealChegada());
        cidv.observacaoTA.setText(idv.getObservacoes());

        //Recebe a data do BD da forma yyyy-mm-dd
        Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(idv.getData());

        //passa a data formatada dd/MM/yyyy
       cidv.dataViagemFTF.setText(new SimpleDateFormat("dd/MM/yyyy").format(dt));
        //cidv.dataViagemFTF.setText(new SimpleDateFormat("dd/MM/yyyy").format(idv.getData()));
        //cidv.dataViagemFTF.setText(idv.getData());

        cidv.idSeqCarroTF.setText(String.valueOf(idv.getIdSeqCarro()));
        cidv.idSeqMotoristaTF.setText(String.valueOf(idv.getIdSeqMotorista()));

        return cidv;
    }
} // fim da classe DisplayQueryFilme

