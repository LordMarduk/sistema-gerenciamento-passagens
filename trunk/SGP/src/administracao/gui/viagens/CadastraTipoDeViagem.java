package administracao.gui.viagens;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import administracao.database.DataBaseManagerImpl;
import administracao.viagens.TipoDeViagem;
import util.JNumericField;

public class CadastraTipoDeViagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    //protected JLabel[] rotulos = new JLabel[20];
    public JTextField identificadorSequencialTF;
    public JTextField horaDeSaidaTF;
    public JTextField valorViagemTF;
    public JTextField horaDeChegadaTF;
    public JTextField idSeqRodovPartida;
    public JTextField idSeqRodovChegada;
    public JCheckBox segundaCB;
    public JCheckBox tercaCB;
    public JCheckBox quartaCB;
    public JCheckBox quintaCB;
    public JCheckBox sextaCB;
    public JCheckBox sabadoCB;
    public JCheckBox domingoCB;
    protected JButton cadastrarTdvButton;
    protected JButton updateTdvButton;
    protected JButton deleteTdvButton;
    protected JButton sair;
    protected String diasDaSemana = "";
    protected int tipoOperacao = 0;

    public DataBaseManagerImpl dbm;

    //protected Filme filmeCriado;
    public CadastraTipoDeViagem(int tipoOperacao, DataBaseManagerImpl dbm) {

        //--Informa��es da Janela--
        super("Cadastrar Tipo De Viagem");
        setLayout(null);
        setSize(440, 380);
        setResizable(false);
        setLocation(250, 100);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //---------------------

        this.dbm = dbm;

        //---------------------

        this.tipoOperacao = tipoOperacao;


        identificadorSequencialTF = new JNumericField(2);
        //identificadorSequencialTF.setFont(padrao);
        identificadorSequencialTF.setBounds(10, 20, 140, 40);
        identificadorSequencialTF.setEditable(false);

         identificadorSequencialTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Identificador sequencial", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(identificadorSequencialTF);
        //--------------------

        //--Valor Viagem--
        //rotulos[1] = new JLabel("Valor Viagem");
        //rotulos[1].setFont(padraoParaRotulos);
        //rotulos[1].setBounds(10, 60, 75, 20);
        //add(rotulos[1]);

        valorViagemTF = new JTextField("");
        //valorViagemTF.setFont(padrao);
        valorViagemTF.setBounds(10, 80, 80, 40);
        valorViagemTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Valor Viagem", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(valorViagemTF);
        //---------------------

        //--Campo Hora De Saida(Previsao)--
        //rotulos[2] = new JLabel("Hora De Saida(Previsao)");
        //rotulos[2].setFont(padraoParaRotulos);
        //rotulos[2].setBounds(120, 60, 150, 20);
        //add(rotulos[2]);

        horaDeSaidaTF = new JTextField("");
        //horaDeSaidaTF.setFont(padrao);
        horaDeSaidaTF.setBounds(120, 80, 120, 40);
        horaDeSaidaTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Hora saida(Previsao)", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(horaDeSaidaTF);
        //---------------------

        //--Hora De Chegada(Previsao)--
        //rotulos[3] = new JLabel("Hora De Chegada(Previsao)");
        //rotulos[3].setFont(padraoParaRotulos);
        //rotulos[3].setBounds(260, 60, 150, 20);
        //add(rotulos[3]);

        horaDeChegadaTF = new JTextField("", 5);
        //horaDeChegadaTF.setFont(padrao);
        horaDeChegadaTF.setBounds(260, 80, 130, 40);
        horaDeChegadaTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Hora chegada(Previsao)", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(horaDeChegadaTF);
        //---------------------


        //--dias da semana--
        segundaCB = new JCheckBox("Segunda");
        segundaCB.setFont(padraoParaRotulos);
        segundaCB.setBounds(10, 140, 100, 20);
        add(segundaCB);
        //segundaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        tercaCB = new JCheckBox("Terca");
        tercaCB.setFont(padraoParaRotulos);
        tercaCB.setBounds(110, 140, 100, 20);
        add(tercaCB);
        //tercaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        quartaCB = new JCheckBox("Quarta");
        quartaCB.setFont(padraoParaRotulos);
        quartaCB.setBounds(210, 140, 100, 20);
        add(quartaCB);
        //quartaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        quintaCB = new JCheckBox("Quinta");
        quintaCB.setFont(padraoParaRotulos);
        quintaCB.setBounds(10, 180, 100, 20);
        add(quintaCB);
        //quintaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        sextaCB = new JCheckBox("Sexta");
        sextaCB.setFont(padraoParaRotulos);
        sextaCB.setBounds(110, 180, 100, 20);
        add(sextaCB);
        //sextaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        sabadoCB = new JCheckBox("Sabado");
        sabadoCB.setFont(padraoParaRotulos);
        sabadoCB.setBounds(210, 180, 100, 20);
        add(sabadoCB);
        //sabadoCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        domingoCB = new JCheckBox("Domingo");
        domingoCB.setFont(padraoParaRotulos);
        domingoCB.setBounds(10, 220, 100, 20);
        add(domingoCB);
        //domingoCB.addItemListener(new CheckBoxHandler());
        //---------------------



        //--Id. Seq. Rodoviaria Partida--
        //rotulos[4] = new JLabel("Id. Seq. Rodoviaria Partida");
        //rotulos[4].setFont(padraoParaRotulos);
        //rotulos[4].setBounds(10, 250, 150, 20);
        // add(rotulos[4]);

        idSeqRodovPartida = new JNumericField(2);
        //idSeqRodovPartida.setFont(padrao);
        idSeqRodovPartida.setBounds(10, 250, 170, 40);
        idSeqRodovPartida.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Id. Seq. Rodoviaria Partida", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(idSeqRodovPartida);
        //--------------------

        //--Id. Seq. Rodoviaria Partida--
        //rotulos[5] = new JLabel("Id. Seq. Rodoviaria Chegada");
        //rotulos[5].setFont(padraoParaRotulos);
        //rotulos[5].setBounds(10, 290, 155, 20);
        //add(rotulos[5]);

        idSeqRodovChegada = new JNumericField(2);
        //idSeqRodovChegada.setFont(padrao);
        idSeqRodovChegada.setBounds(10, 310, 170, 40);
        idSeqRodovChegada.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Id. Seq. Rodoviaria Chegada", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(idSeqRodovChegada);
        //--------------------



        //--Bot�o sair--
        sair = new JButton("Sair");
        sair.setFont(padraoParaRotulos);
        sair.setBounds(300, 310, 100, 20);
        add(sair);

        sair.addActionListener(new ButtonHandlerSai());
        //--------------------

        //--Bot�o cadastrar--
        cadastrarTdvButton = new JButton("Cadastrar");
        cadastrarTdvButton.setFont(padraoParaRotulos);
        cadastrarTdvButton.setBounds(300, 285, 100, 20);
        if (tipoOperacao == 1) {
            cadastrarTdvButton.setEnabled(false);
        }
        add(cadastrarTdvButton);

        cadastrarTdvButton.addActionListener(new ButtonHandlerCadastra());
        //--------------------

        //--Bot�o Atualizar--
        updateTdvButton = new JButton("Atualizar");
        updateTdvButton.setFont(padraoParaRotulos);
        updateTdvButton.setBounds(200, 285, 100, 20);
        if (tipoOperacao == 0) {
            updateTdvButton.setEnabled(false);
        }
        add(updateTdvButton);

        updateTdvButton.addActionListener(new ButtonHandlerAtualiza());
        //--------------------

        //--Bot�o Apagar--
        deleteTdvButton = new JButton("Apagar");
        deleteTdvButton.setFont(padraoParaRotulos);
        deleteTdvButton.setBounds(200, 310, 100, 20);
        if (tipoOperacao == 0) {
            deleteTdvButton.setEnabled(false);
        }
        add(deleteTdvButton);

        deleteTdvButton.addActionListener(new ButtonHandlerApaga());
        //--------------------

        getGraphicsConfiguration();

    }

    private class ButtonHandlerSai implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            dispose();
        }
    }

    private class ButtonHandlerCadastra implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            try {
                TipoDeViagem tdv = insereGuiEmObjeto();
                dbm.insertTipoDeViagem(tdv);

            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    private class ButtonHandlerAtualiza implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            try {
                TipoDeViagem tdv = insereGuiEmObjeto();
                dbm.updateTipoDeViagem(tdv.getIdSeqTdv(), tdv);
            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    private class ButtonHandlerApaga implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            try {
                TipoDeViagem tdv = insereGuiEmObjeto();
                dbm.deleteTipoDeViagem(tdv.getIdSeqTdv());
            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    public TipoDeViagem insereGuiEmObjeto() throws Exception {

        //check buttons:
        if (segundaCB.isSelected()) {
            diasDaSemana = diasDaSemana.concat("segunda ");
        }
        if (tercaCB.isSelected()) {
            diasDaSemana = diasDaSemana.concat("terca ");
        }
        if (quartaCB.isSelected()) {
            diasDaSemana = diasDaSemana.concat("quarta ");
        }
        if (quintaCB.isSelected()) {
            diasDaSemana = diasDaSemana.concat("quinta ");
        }
        if (sextaCB.isSelected()) {
            diasDaSemana = diasDaSemana.concat("sexta ");
        }
        if (sabadoCB.isSelected()) {
            diasDaSemana = diasDaSemana.concat("sabado ");
        }
        if (domingoCB.isSelected()) {
            diasDaSemana = diasDaSemana.concat("domingo ");
        }

        TipoDeViagem tdv = new TipoDeViagem();
        if (tipoOperacao == 1) {
            tdv.setIdSeqTdv(Integer.parseInt(identificadorSequencialTF.getText()));
        }

        tdv.setValorViagem(Float.parseFloat(valorViagemTF.getText()));
        tdv.setHoraPrevSaida(horaDeSaidaTF.getText());
        tdv.setHoraPrevChegada(horaDeChegadaTF.getText());
        tdv.setDiasDaSemana(diasDaSemana);
        tdv.setIdSeqRodovPartida(Integer.parseInt(idSeqRodovPartida.getText()));
        tdv.setIdSeqRodovChegada(Integer.parseInt(idSeqRodovChegada.getText()));

        return tdv;
    }
}
