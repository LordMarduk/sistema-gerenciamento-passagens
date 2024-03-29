package administracao.gui.viagens;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import administracao.database.DataBaseManagerImpl;
import administracao.viagens.TipoDeViagem;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import util.Auxiliares;
import util.JNumericField;


public class CadastraTipoDeViagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    //protected JLabel[] rotulos = new JLabel[20];
    public JTextField identificadorSequencialTF;
    public JTextField horaDeSaidaTF;
    public JTextField valorViagemTF;
    public JTextField horaDeChegadaTF;
    public JNumericField idSeqRodovPartida;
    public JNumericField idSeqRodovChegada;
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
    protected JButton pegarIdSeqRodovPartida;
    protected JButton pegarIdSeqRodovChegada;
    protected JButton sair;
    protected String diasDaSemana = "";
    protected int tipoOperacao = 0;


    public DataBaseManagerImpl dbm;

    //protected Filme filmeCriado;
    public CadastraTipoDeViagem(int tipoOperacao, final DataBaseManagerImpl dbm) {

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
        
        //---------------------
        //--dias da semana--
        tercaCB = new JCheckBox("Terca");
        tercaCB.setFont(padraoParaRotulos);
        tercaCB.setBounds(110, 140, 100, 20);
        add(tercaCB);
        
        //---------------------
        //--dias da semana--
        quartaCB = new JCheckBox("Quarta");
        quartaCB.setFont(padraoParaRotulos);
        quartaCB.setBounds(210, 140, 100, 20);
        add(quartaCB);
        
        //---------------------
        //--dias da semana--
        quintaCB = new JCheckBox("Quinta");
        quintaCB.setFont(padraoParaRotulos);
        quintaCB.setBounds(10, 180, 100, 20);
        add(quintaCB);
        
        //---------------------
        //--dias da semana--
        sextaCB = new JCheckBox("Sexta");
        sextaCB.setFont(padraoParaRotulos);
        sextaCB.setBounds(110, 180, 100, 20);
        add(sextaCB);
        
        //---------------------
        //--dias da semana--
        sabadoCB = new JCheckBox("Sabado");
        sabadoCB.setFont(padraoParaRotulos);
        sabadoCB.setBounds(210, 180, 100, 20);
        add(sabadoCB);
       
        //---------------------
        //--dias da semana--
        domingoCB = new JCheckBox("Domingo");
        domingoCB.setFont(padraoParaRotulos);
        domingoCB.setBounds(10, 220, 100, 20);
        add(domingoCB);
        
        //---------------------



        //--Id. Seq. Rodoviaria Partida--
        
        idSeqRodovPartida = new JNumericField(2);
        //idSeqRodovPartida.setFont(padrao);
        idSeqRodovPartida.setBounds(10, 250, 170, 40);
        idSeqRodovPartida.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Id. Seq. Rodoviaria Partida", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(idSeqRodovPartida);
        
        pegarIdSeqRodovPartida = new JButton("...");
        pegarIdSeqRodovPartida.setBounds(180, 260, 20, 20);
        pegarIdSeqRodovPartida.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        new SelecionarIds(dbm,2,idSeqRodovPartida,null);
                    }
                }
        );
        add(pegarIdSeqRodovPartida);
        //--------------------

        //--Id. Seq. Rodoviaria Chegada--
     
        idSeqRodovChegada = new JNumericField(2);
        //idSeqRodovChegada.setFont(padrao);
        idSeqRodovChegada.setBounds(10, 310, 170, 40);
        idSeqRodovChegada.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Id. Seq. Rodoviaria Chegada", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(idSeqRodovChegada);

        pegarIdSeqRodovChegada = new JButton("...");
        pegarIdSeqRodovChegada.setBounds(180, 320, 20, 20);
        pegarIdSeqRodovChegada.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        new SelecionarIds(dbm,3,idSeqRodovChegada,null);
                    }
                }
        );
        add(pegarIdSeqRodovChegada);
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
        if (tipoOperacao == Auxiliares.ALTERAR) {
            cadastrarTdvButton.setEnabled(false);
        }
        add(cadastrarTdvButton);

        cadastrarTdvButton.addActionListener(new ButtonHandlerCadastra());
        //--------------------

        //--Bot�o Atualizar--
        updateTdvButton = new JButton("Atualizar");
        updateTdvButton.setFont(padraoParaRotulos);
        updateTdvButton.setBounds(200, 285, 100, 20);
        if (tipoOperacao == Auxiliares.CADASTRAR) {
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

            //tratamento campo valor viagem
                        if(valorViagemTF.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                            null,
                            "Campo Valor Viagem Obrigatório",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                            return;
                        }


            //tratamento horaDeSaidaTF
                        Pattern p = Pattern.compile("[0-1]{1}[0-9]{1}[\\:][0-5]{1}[0-9]{1}|"+
                                                    "[2]{1}[0-3]{1}[\\:][0-5]{1}[0-9]{1}");
                        Matcher m = p.matcher(horaDeSaidaTF.getText());
                        if(!(m.find())){
                            JOptionPane.showMessageDialog
                                (null, "Hora Saída Inválida!\nFormato> hh:mm", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

            //tratamento horaDeSaidaTF
                        Pattern p2 = Pattern.compile("[0-1]{1}[0-9]{1}[\\:][0-5]{1}[0-9]{1}|"+
                                                    "[2]{1}[0-3]{1}[\\:][0-5]{1}[0-9]{1}");
                        Matcher m2 = p2.matcher(horaDeChegadaTF.getText());
                        if(!(m2.find())){
                            JOptionPane.showMessageDialog
                                (null, "Hora Chegada Inválida!\nFormato> hh:mm", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

            //tratamento campo idSeqRodovPartida
                        if(idSeqRodovPartida.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                            null,
                            "Campo Id Seq Rodoviaria Partida Obrigatório",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                            return;
                        }

            //tratamento campo idSeqRodovChegada
                        if(idSeqRodovChegada.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                            null,
                            "Campo Id Seq Rodoviaria Chegada Obrigatório",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                            return;
                        }
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

            //tratamento campo valor viagem
                        if(valorViagemTF.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                            null,
                            "Campo Valor Viagem Obrigatório",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                            return;
                        }


            //tratamento horaDeSaidaTF
                        Pattern p = Pattern.compile("[0-1]{1}[0-9]{1}[\\:][0-5]{1}[0-9]{1}|"+
                                                    "[2]{1}[0-3]{1}[\\:][0-5]{1}[0-9]{1}|");
                        Matcher m = p.matcher(horaDeSaidaTF.getText());
                        if(!(m.find())){
                            JOptionPane.showMessageDialog
                                (null, "Hora Inválida!\nFormato> hh:mm", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

            //tratamento horaDeSaidaTF
                        Pattern p2 = Pattern.compile("[0-1]{1}[0-9]{1}[\\:][0-5]{1}[0-9]{1}|"+
                                                    "[2]{1}[0-3]{1}[\\:][0-5]{1}[0-9]{1}|");
                        Matcher m2 = p2.matcher(horaDeChegadaTF.getText());
                        if(!(m2.find())){
                            JOptionPane.showMessageDialog
                                (null, "Hora Inválida!\nFormato> hh:mm", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

            //tratamento campo idSeqRodovPartida
                        if(idSeqRodovPartida.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                            null,
                            "Campo Id Seq Rodoviaria Partida Obrigatório",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                            return;
                        }

            //tratamento campo idSeqRodovChegada
                        if(idSeqRodovChegada.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                            null,
                            "Campo Id Seq Rodoviaria Chegada Obrigatório",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                            return;
                        }
                        
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
