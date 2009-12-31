package administracao.gui.viagens;

import java.text.FieldPosition;
import java.text.ParsePosition;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import administracao.database.DataBaseManagerImpl;
import javax.swing.JComboBox;
import util.Auxiliares;
import administracao.viagens.InstanciaDeViagem;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import util.JNumericField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class CadastraInstanciaDeViagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    public JNumericField idSeqTdvTF;
    public JTextField horaDeSaidaTF;
    public JNumericField numVagasDisponiveisTF;
    public JTextField horaDeChegadaTF;
    public JNumericField idSeqCarroTF;
    public JNumericField idSeqMotoristaTF;
    protected JButton cadastrarIdvButton;
    protected JButton updateIdvButton;
    protected JButton deleteIdvButton;
    protected JButton sair;
    protected JTextArea observacaoTA;
    protected JFormattedTextField dataViagemFTF;
    MaskFormatter formatter = null;
    protected int tipoOperacao = 0;
    public DataBaseManagerImpl dbm;

    //protected Filme filmeCriado;
    public CadastraInstanciaDeViagem(int tipoOperacao, DataBaseManagerImpl dbm) {

        //--Informa��es da Janela--
        super("Cadastrar Instancia De Viagem");
        setLayout(null);
        setSize(440, 380);
        setResizable(false);
        setLocation(250, 100);
        setVisible(true);
        //---------------------

        this.dbm = dbm;

        this.tipoOperacao = tipoOperacao;

        idSeqTdvTF = new JNumericField(2);
        idSeqTdvTF.setFont(padrao);
        idSeqTdvTF.setBounds(10, 30, 130, 40);
        idSeqTdvTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Id. Seq. Tipo De Viagem", 0, 0, new Font("Tahoma", 0, 10)));

        add(idSeqTdvTF);
        //--------------------

        //--Vagas Disponiveis--
        numVagasDisponiveisTF = new JNumericField(2);
        numVagasDisponiveisTF.setFont(padrao);
        numVagasDisponiveisTF.setBounds(170, 30, 150, 40);
        //depois pegar isso de carro no banco de dados(capacidade)
        //numVagasDisponiveisTF.setEditable(false);
        numVagasDisponiveisTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Numero De Vagas Disponiveis", 0, 0, new Font("Tahoma", 0, 10)));
        add(numVagasDisponiveisTF);

        //---------------------

        //--Campo Hora De Saida--
        horaDeSaidaTF = new JTextField("");
        horaDeSaidaTF.setFont(padrao);
        horaDeSaidaTF.setBounds(10, 80, 130, 40);
        add(horaDeSaidaTF);
        horaDeSaidaTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Horario De Saida", 0, 0, new Font("Tahoma", 0, 10)));
        add(horaDeSaidaTF);

        horaDeChegadaTF = new JTextField("", 5);
        horaDeChegadaTF.setFont(padrao);
        horaDeChegadaTF.setBounds(170, 80, 130, 40);
        horaDeChegadaTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Horario De Chegada", 0, 0, new Font("Tahoma", 0, 10)));
        add(horaDeChegadaTF);

        //---------------------
        //--observacoes--

        observacaoTA = new JTextArea();
        observacaoTA.setBorder(
                BorderFactory.createTitledBorder(
                null, "Observacoes:", 0, 0, new Font("Tahoma", 0, 10)));

        //observacaoTA.setText(f.retornaobservacaoTA());
        //observacaoTA.setBackground(this.getBackground());
        observacaoTA.setBounds(200, 170, 200, 100);
        //observacaoTA.setEditable(false);
        //observacaoTA.setEnabled(false);
        add(observacaoTA);

        //data
        //escolhaDeDia = new JComboBox(Auxiliares.DIAS);
        //escolhaDeDia.setMaximumRowCount(4);
        //escolhaDeDia.setFont(padrao);
        //escolhaDeDia.setSelectedIndex(0);
        //escolhaDeDia.setBounds(10, 160,60, 20);
        //add(escolhaDeDia);
        try {
            formatter = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        dataViagemFTF = new javax.swing.JFormattedTextField(formatter);

        dataViagemFTF.setBounds(10, 170, 120, 40);
        dataViagemFTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Data Da Viagem *", 0, 0, new Font("Tahoma", 0, 10)));
        add(dataViagemFTF);


        //---------------------


        //--Id. Seq. Carro--
        idSeqCarroTF = new JNumericField(2);
        idSeqCarroTF.setFont(padrao);
        idSeqCarroTF.setBounds(10, 220, 120, 40);
        idSeqCarroTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Id. Seq. Carro", 0, 0, new Font("Tahoma", 0, 10)));

        add(idSeqCarroTF);
        //--------------------
        //--Id. Seq. Rodoviaria Partida--

        idSeqMotoristaTF = new JNumericField(3);
        idSeqMotoristaTF.setFont(padrao);
        idSeqMotoristaTF.setBounds(10, 270, 120, 40);
        idSeqMotoristaTF.setBorder(
                BorderFactory.createTitledBorder(
                null, "Id. Seq. Motorista", 0, 0, new Font("Tahoma", 0, 10)));

        add(idSeqMotoristaTF);
        //--------------------

        //--Bot�o sair--
        sair = new JButton("Sair");
        sair.setFont(padraoParaRotulos);
        sair.setBounds(300, 310, 100, 20);
        add(sair);

        sair.addActionListener(new ButtonHandlerSai());
        //--------------------

        //--Bot�o cadastrar--
        cadastrarIdvButton = new JButton("Cadastrar");
        cadastrarIdvButton.setFont(padraoParaRotulos);
        cadastrarIdvButton.setBounds(300, 285, 100, 20);
        if (tipoOperacao == 1) {
            cadastrarIdvButton.setEnabled(false);
        }
        add(cadastrarIdvButton);

        cadastrarIdvButton.addActionListener(new ButtonHandlerCadastra());
        //--------------------

        //--Bot�o Atualizar--
        updateIdvButton = new JButton("Atualizar");
        updateIdvButton.setFont(padraoParaRotulos);
        updateIdvButton.setBounds(200, 285, 100, 20);
        if (tipoOperacao == 0) {
            updateIdvButton.setEnabled(false);
        }
        add(updateIdvButton);

        updateIdvButton.addActionListener(new ButtonHandlerAtualiza());
        //--------------------

        //--Bot�o Apagar--
        deleteIdvButton = new JButton("Apagar");
        deleteIdvButton.setFont(padraoParaRotulos);
        deleteIdvButton.setBounds(200, 310, 100, 20);
        if (tipoOperacao == 0) {
            deleteIdvButton.setEnabled(false);
        }
        add(deleteIdvButton);

        deleteIdvButton.addActionListener(new ButtonHandlerApaga());
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
                InstanciaDeViagem idv = insereGuiEmObjeto();
                dbm.insertInstanciaDeViagem(idv);

            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    private class ButtonHandlerAtualiza implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            try {
                InstanciaDeViagem idv = insereGuiEmObjeto();
                dbm.updateInstanciaDeViagem(idv.getIdSeqTdv(),idv.getData(), idv);
            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    private class ButtonHandlerApaga implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            try {
                InstanciaDeViagem idv = insereGuiEmObjeto();
                dbm.deleteInstanciaDeViagem(idv.getIdSeqTdv(),idv.getData());
                

            } catch (Exception e) {
            }
            dispose();
        }//fim de ActionPerformed
    }//fim da classe interna ButtonHandler

    public InstanciaDeViagem insereGuiEmObjeto() throws Exception {

        InstanciaDeViagem idv = new InstanciaDeViagem();

        idv.setIdSeqTdv(Integer.parseInt(idSeqTdvTF.getText()));
        idv.setHoraRealSaida(horaDeSaidaTF.getText());
        idv.setHoraRealChegada(horaDeChegadaTF.getText());
        idv.setIdSeqCarro(Integer.parseInt(idSeqCarroTF.getText()));
        idv.setIdSeqMotorista(Integer.parseInt(idSeqMotoristaTF.getText()));
        idv.setObservacoes(observacaoTA.getText());

        idv.setData(dataViagemFTF.getText());

        idv.setNumVagasDisponiveis(Integer.parseInt(numVagasDisponiveisTF.getText()));

        return idv;
    }
}
