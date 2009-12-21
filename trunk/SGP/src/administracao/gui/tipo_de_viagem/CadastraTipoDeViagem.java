package administracao.gui.tipo_de_viagem;

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
import administracao.database.DataBaseManagerImpl;
import administracao.tipo_de_viagem.TipoDeViagem;

public class CadastraTipoDeViagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    protected JLabel[] rotulos = new JLabel[20];
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
        //---------------------

        this.dbm = dbm;

        //---------------------

        this.tipoOperacao = tipoOperacao;

        //--Identificador Sequencial--
        rotulos[0] = new JLabel("Identificador Sequencial");
        rotulos[0].setFont(padraoParaRotulos);
        rotulos[0].setBounds(10, 10, 150, 20);
        add(rotulos[0]);

        identificadorSequencialTF = new JTextField("");
        identificadorSequencialTF.setFont(padrao);
        identificadorSequencialTF.setBounds(10, 30, 150, 20);
        identificadorSequencialTF.setEditable(false);
        add(identificadorSequencialTF);
        //--------------------

        //--Valor Viagem--
        rotulos[1] = new JLabel("Valor Viagem");
        rotulos[1].setFont(padraoParaRotulos);
        rotulos[1].setBounds(10, 60, 75, 20);
        add(rotulos[1]);

        valorViagemTF = new JTextField("");
        valorViagemTF.setFont(padrao);
        valorViagemTF.setBounds(10, 80, 50, 20);
        add(valorViagemTF);
        //---------------------

        //--Campo Hora De Saida(Previsao)--
        rotulos[2] = new JLabel("Hora De Saida(Previsao)");
        rotulos[2].setFont(padraoParaRotulos);
        rotulos[2].setBounds(120, 60, 150, 20);
        add(rotulos[2]);

        horaDeSaidaTF = new JTextField("");
        horaDeSaidaTF.setFont(padrao);
        horaDeSaidaTF.setBounds(120, 80, 50, 20);
        add(horaDeSaidaTF);
        //---------------------

        //--Hora De Chegada(Previsao)--
        rotulos[3] = new JLabel("Hora De Chegada(Previsao)");
        rotulos[3].setFont(padraoParaRotulos);
        rotulos[3].setBounds(260, 60, 150, 20);
        add(rotulos[3]);

        horaDeChegadaTF = new JTextField("", 5);
        horaDeChegadaTF.setFont(padrao);
        horaDeChegadaTF.setBounds(260, 80, 50, 20);
        add(horaDeChegadaTF);
        //---------------------


        //--dias da semana--
        segundaCB = new JCheckBox("Segunda");
        segundaCB.setFont(padraoParaRotulos);
        segundaCB.setBounds(10, 120, 100, 20);
        add(segundaCB);
        segundaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        tercaCB = new JCheckBox("Terca");
        tercaCB.setFont(padraoParaRotulos);
        tercaCB.setBounds(110, 120, 100, 20);
        add(tercaCB);
        tercaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        quartaCB = new JCheckBox("Quarta");
        quartaCB.setFont(padraoParaRotulos);
        quartaCB.setBounds(210, 120, 100, 20);
        add(quartaCB);
        quartaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        quintaCB = new JCheckBox("Quinta");
        quintaCB.setFont(padraoParaRotulos);
        quintaCB.setBounds(10, 160, 100, 20);
        add(quintaCB);
        quintaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        sextaCB = new JCheckBox("Sexta");
        sextaCB.setFont(padraoParaRotulos);
        sextaCB.setBounds(110, 160, 100, 20);
        add(sextaCB);
        sextaCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        sabadoCB = new JCheckBox("Sabado");
        sabadoCB.setFont(padraoParaRotulos);
        sabadoCB.setBounds(210, 160, 100, 20);
        add(sabadoCB);
        sabadoCB.addItemListener(new CheckBoxHandler());
        //---------------------
        //--dias da semana--
        domingoCB = new JCheckBox("Domingo");
        domingoCB.setFont(padraoParaRotulos);
        domingoCB.setBounds(10, 200, 100, 20);
        add(domingoCB);
        domingoCB.addItemListener(new CheckBoxHandler());
        //---------------------



        //--Id. Seq. Rodoviaria Partida--
        rotulos[4] = new JLabel("Id. Seq. Rodoviaria Partida");
        rotulos[4].setFont(padraoParaRotulos);
        rotulos[4].setBounds(10, 250, 150, 20);
        add(rotulos[4]);

        idSeqRodovPartida = new JTextField("");
        idSeqRodovPartida.setFont(padrao);
        idSeqRodovPartida.setBounds(10, 270, 20, 20);
        add(idSeqRodovPartida);
        //--------------------

        //--Id. Seq. Rodoviaria Partida--
        rotulos[5] = new JLabel("Id. Seq. Rodoviaria Chegada");
        rotulos[5].setFont(padraoParaRotulos);
        rotulos[5].setBounds(10, 290, 155, 20);
        add(rotulos[5]);

        idSeqRodovChegada = new JTextField("");
        idSeqRodovChegada.setFont(padrao);
        idSeqRodovChegada.setBounds(10, 310, 20, 20);
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

    private class CheckBoxHandler implements ItemListener {

        public void itemStateChanged(ItemEvent event) {

            /*
            if(event.getItemSelectable().equals(segundaCB) && segundaCB.isSelected() == true)
            diasDaSemana = diasDaSemana.concat("segunda ");
            else if(event.getItemSelectable().equals(tercaCB) && tercaCB.isSelected() == true)
            diasDaSemana = diasDaSemana.concat("terca ");
            else if(event.getItemSelectable().equals(quartaCB) && quartaCB.isSelected() == true)
            diasDaSemana = diasDaSemana.concat("quarta ");
            else if(event.getItemSelectable().equals(quintaCB) && quintaCB.isSelected() == true)
            diasDaSemana = diasDaSemana.concat("quinta ");
            else if(event.getItemSelectable().equals(sextaCB) && sextaCB.isSelected() == true)
            diasDaSemana = diasDaSemana.concat("sexta ");
            else if(event.getItemSelectable().equals(sabadoCB) && sabadoCB.isSelected() == true)
            diasDaSemana = diasDaSemana.concat("sabado ");
            else if(event.getItemSelectable().equals(domingoCB) && domingoCB.isSelected() == true)
            diasDaSemana = diasDaSemana.concat("domingo ");
             */
            /*
            diasDaSemana = segundaCB.isSelected() ? diasDaSemana.concat("segunda ") : diasDaSemana.concat("");
            diasDaSemana = tercaCB.isSelected() ? diasDaSemana.concat("terca ") : diasDaSemana.concat("");
            diasDaSemana = quartaCB.isSelected() ? diasDaSemana.concat("quarta ") : diasDaSemana.concat("");
            diasDaSemana = quintaCB.isSelected() ? diasDaSemana.concat("quinta ") : diasDaSemana.concat("");
            diasDaSemana = sextaCB.isSelected() ? diasDaSemana.concat("sexta ") : diasDaSemana.concat("");
            diasDaSemana = sabadoCB.isSelected() ? diasDaSemana.concat("sabado ") : diasDaSemana.concat("");
            diasDaSemana = domingoCB.isSelected() ? diasDaSemana.concat("domingo ") : diasDaSemana.concat("");
             */
        }
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
