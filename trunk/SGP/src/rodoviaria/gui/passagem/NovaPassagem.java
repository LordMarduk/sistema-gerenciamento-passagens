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
import javax.swing.JComboBox;
import util.Auxiliares;

public class NovaPassagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    protected JLabel[] rotulos = new JLabel[20];
    public JTextField idSeqTdvTF;
    public JTextField valorTotalTF;
    public JTextField idSeqAgenteTF;
    public JTextField idSeqClienteTF;
    
    protected JButton cadastrarNpButton;
    protected JButton updateNpButton;
    protected JButton deleteNpButton;
    protected JButton sair;
    
    protected JCheckBox estudanteCB;
    
    protected JComboBox escolhaDeDia;
    protected JComboBox escolhaDeMes;
    protected JComboBox escolhaDeAno;
    protected JComboBox escolhaDePoltrona;
    
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
        rotulos[0] = new JLabel("Id. Seq. Tipo De Viagem");
        rotulos[0].setFont(padraoParaRotulos);
        rotulos[0].setBounds(10, 10, 150, 20);
        add(rotulos[0]);

        idSeqTdvTF = new JTextField("");
        idSeqTdvTF.setFont(padrao);
        idSeqTdvTF.setBounds(10, 30, 20, 20);
        add(idSeqTdvTF);
        //--------------------

        //--Valor Total--
        rotulos[1] = new JLabel("Valor Total");
        rotulos[1].setFont(padraoParaRotulos);
        rotulos[1].setBounds(10, 60, 120, 20);
        add(rotulos[1]);

        valorTotalTF = new JTextField("");
        valorTotalTF.setFont(padrao);
        valorTotalTF.setBounds(10, 80, 50, 20);
        valorTotalTF.setEditable(false);
        add(valorTotalTF);
        //---------------------

        //--Estudante-//
        
        estudanteCB = new JCheckBox("Estudante",null,false);
        estudanteCB.setBounds(190, 150, 100, 20);
        add(estudanteCB);
        
        //---------------------

        //--Poltrona--
        rotulos[3] = new JLabel("Poltrona");
        rotulos[3].setFont(padraoParaRotulos);
        rotulos[3].setBounds(190, 60, 75, 20);
        add(rotulos[3]);
        
        escolhaDePoltrona = new JComboBox(Auxiliares.NUMERO_POLTRONA);
        escolhaDePoltrona.setMaximumRowCount(4);
        escolhaDePoltrona.setFont(padrao);
        escolhaDePoltrona.setSelectedIndex(0);
        escolhaDePoltrona.setBounds(190, 80,60, 20);
        add(escolhaDePoltrona);
       
        //---------------------

        //data
        rotulos[4] = new JLabel("Data");
        rotulos[4].setFont(padraoParaRotulos);
        rotulos[4].setBounds(10, 110, 75, 20);
        add(rotulos[4]);

        escolhaDeDia = new JComboBox(Auxiliares.DIAS);
        escolhaDeDia.setMaximumRowCount(4);
        escolhaDeDia.setFont(padrao);
        escolhaDeDia.setSelectedIndex(0);
        escolhaDeDia.setBounds(10, 130,60, 20);
        add(escolhaDeDia);

        escolhaDeMes = new JComboBox(Auxiliares.MESES);
        escolhaDeMes.setMaximumRowCount(4);
        escolhaDeMes.setFont(padrao);
        escolhaDeMes.setSelectedIndex(0);
        escolhaDeMes.setBounds(10, 160,80, 20);
        add(escolhaDeMes);

        escolhaDeAno = new JComboBox(Auxiliares.ANOS);
        escolhaDeAno.setMaximumRowCount(4);
        escolhaDeAno.setFont(padrao);
        escolhaDeAno.setSelectedIndex(0);
        escolhaDeAno.setBounds(10, 190,60, 20);
        add(escolhaDeAno);
        //---------------------


        //--Id. Seq. Agente--
        rotulos[5] = new JLabel("Id. Seq. Agente");
        rotulos[5].setFont(padraoParaRotulos);
        rotulos[5].setBounds(10, 250, 150, 20);
        add(rotulos[5]);

        idSeqAgenteTF = new JTextField("");
        idSeqAgenteTF.setFont(padrao);
        idSeqAgenteTF.setBounds(10, 270, 20, 20);
        add(idSeqAgenteTF);
        //--------------------

        //--Id. Seq. Cliente--
        rotulos[6] = new JLabel("Id. Seq. Cliente");
        rotulos[6].setFont(padraoParaRotulos);
        rotulos[6].setBounds(10, 290, 155, 20);
        add(rotulos[6]);

        idSeqClienteTF = new JTextField("");
        idSeqClienteTF.setFont(padrao);
        idSeqClienteTF.setBounds(10, 310, 30, 20);
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

