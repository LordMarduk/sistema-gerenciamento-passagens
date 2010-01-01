package rodoviaria.gui.passagem;

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
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import util.Auxiliares;
import util.JNumericField;

public class NovaPassagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    protected JLabel[] rotulos = new JLabel[20];
    public JNumericField idSeqTdvTF;
    public JTextField valorTotalTF;
    public JNumericField idSeqAgenteTF;
    public JNumericField idSeqClienteTF;
    
    protected JButton cadastrarNpButton;
    protected JButton updateNpButton;
    protected JButton deleteNpButton;
    protected JButton sair;
    
    protected JCheckBox estudanteCB;
    protected JComboBox escolhaDePoltrona;

    protected JFormattedTextField dataViagemFTF;
    MaskFormatter formatter = null;
    
    protected int tipoOperacao = 0;

    //protected Filme filmeCriado;
    public NovaPassagem(int tipoOperacao) {

        //--Informa��es da Janela--
        super("Nova Passagem");
        setLayout(null);
        setSize(440, 380);
        setResizable(false);
        setLocation(250, 100);
        //---------------------

        this.tipoOperacao = tipoOperacao;

        //--Identificador Sequencial--

        idSeqTdvTF = new JNumericField(2);
        idSeqTdvTF.setFont(padrao);
        idSeqTdvTF.setBounds(10, 140, 120, 40);
        idSeqTdvTF.setEditable(false);
        idSeqTdvTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Id. Seq. Tipo De Viagem", 0, 0, new Font("Tahoma", 0, 10)));
        add(idSeqTdvTF);
        //--------------------

        //--Valor Total--
        valorTotalTF = new JTextField("");
        valorTotalTF.setFont(padrao);
        valorTotalTF.setBounds(10, 60, 90, 40);
        valorTotalTF.setEditable(false);
        valorTotalTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Valor Total", 0, 0, new Font("Tahoma", 0, 10)));
        add(valorTotalTF);
        //---------------------

        //--Estudante-// 
        estudanteCB = new JCheckBox("Estudante",null,false);
        estudanteCB.setBounds(200, 230,100, 20);
        add(estudanteCB);
        
        //--------------------
        //--Poltrona--
        rotulos[3] = new JLabel("Poltrona");
        rotulos[3].setFont(padraoParaRotulos);
        rotulos[3].setBounds(200, 60, 75, 20);
        add(rotulos[3]);
        
        escolhaDePoltrona = new JComboBox(Auxiliares.NUMERO_POLTRONA);
        escolhaDePoltrona.setMaximumRowCount(4);
        escolhaDePoltrona.setFont(padrao);
        escolhaDePoltrona.setSelectedIndex(0);
        escolhaDePoltrona.setBounds(200, 80,60, 20);
        add(escolhaDePoltrona);
       
        //---------------------

        //data
        try {
            formatter = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        dataViagemFTF = new javax.swing.JFormattedTextField(formatter);
        dataViagemFTF.setBounds(200, 140, 100, 40);
        dataViagemFTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Data Da Viagem *", 0, 0, new Font("Tahoma", 0, 10)));
        dataViagemFTF.setEditable(false);
        add(dataViagemFTF);
        //---------------------


        //--Id. Seq. Agente--
        idSeqAgenteTF = new JNumericField(2);
        idSeqAgenteTF.setFont(padrao);
        idSeqAgenteTF.setBounds(10, 230, 100, 40);
        idSeqAgenteTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Id. Seq. Agente", 0, 0, new Font("Tahoma", 0, 10)));

        add(idSeqAgenteTF);
        //--------------------

        //--Id. Seq. Cliente--
        

        idSeqClienteTF = new JNumericField(5);
        idSeqClienteTF.setFont(padrao);
        idSeqClienteTF.setBounds(10, 300, 100, 40);
        idSeqClienteTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Id. Seq. Cliente", 0, 0, new Font("Tahoma", 0, 10)));
        add(idSeqClienteTF);
        //--------------------



        //--Bot�o sair--
        sair = new JButton("Sair");
        sair.setFont(padraoParaRotulos);
        sair.setBounds(300, 310, 100, 20);
        add(sair);

        sair.addActionListener(new ButtonHandlerSai());
        //--------------------

        //--Bot�o cadastrar--
        cadastrarNpButton = new JButton("Cadastrar");
        cadastrarNpButton.setFont(padraoParaRotulos);
        cadastrarNpButton.setBounds(300, 285, 100, 20);
        if (tipoOperacao == 1) {
            cadastrarNpButton.setEnabled(false);
        }
        add(cadastrarNpButton);

        cadastrarNpButton.addActionListener(new ButtonHandlerCadastra());
        //--------------------

        //--Bot�o Atualizar--
        updateNpButton = new JButton("Atualizar");
        updateNpButton.setFont(padraoParaRotulos);
        updateNpButton.setBounds(200, 285, 100, 20);
        if (tipoOperacao == 0) {
            updateNpButton.setEnabled(false);
        }
        add(updateNpButton);

        updateNpButton.addActionListener(new ButtonHandlerAtualiza());
        //--------------------

        //--Bot�o Apagar--
        deleteNpButton = new JButton("Apagar");
        deleteNpButton.setFont(padraoParaRotulos);
        deleteNpButton.setBounds(200, 310, 100, 20);
        if (tipoOperacao == 0) {
            deleteNpButton.setEnabled(false);
        }
        add(deleteNpButton);

        deleteNpButton.addActionListener(new ButtonHandlerApaga());
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
                //TipoDeViagem tdv = insereGuiEmObjeto();
                //OperacoesTipoDeViagemDB otdv = new OperacoesTipoDeViagemDB();
                //otdv.insertTipoDeViagem(tdv);

            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    private class ButtonHandlerAtualiza implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            try {
                //TipoDeViagem tdv = insereGuiEmObjeto();
                //OperacoesTipoDeViagemDB otdv = new OperacoesTipoDeViagemDB();
                //otdv.updateTipoDeViagem(tdv.getIdSeqTdv(), tdv);
            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    private class ButtonHandlerApaga implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            try {
                //TipoDeViagem tdv = insereGuiEmObjeto();
                //OperacoesTipoDeViagemDB otdv = new OperacoesTipoDeViagemDB();
                //otdv.deleteTipoDeViagem(tdv.getIdSeqTdv());
            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    //public TipoDeViagem insereGuiEmObjeto() throws Exception {

       // TipoDeViagem tdv = new TipoDeViagem();
       // if (tipoOperacao == 1) {
        //   tdv.setIdSeqTdv(Integer.parseInt(idSeqTdvTF.getText()));
        //}

       // tdv.setValorViagem(Float.parseFloat(valorTotalTF.getText()));
        //tdv.setHoraPrevSaida(horaDeSaidaTF.getText());
        //tdv.setHoraPrevChegada(horaDeChegadaTF.getText());
        //tdv.setDiasDaSemana(diasDaSemana);
        //tdv.setsetidSeqAgenteTF(Integer.parseInt(idSeqAgenteTF.getText()));
        //tdv.setidSeqClienteTF(Integer.parseInt(idSeqClienteTF.getText()));

        //return tdv;
    //}
}

