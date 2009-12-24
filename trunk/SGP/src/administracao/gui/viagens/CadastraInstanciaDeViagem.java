package administracao.gui.viagens;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import administracao.database.DataBaseManagerImpl;
import javax.swing.JComboBox;
import javax.xml.crypto.Data;
import util.Auxiliares;
import administracao.viagens.InstanciaDeViagem;

public class CadastraInstanciaDeViagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    protected JLabel[] rotulos = new JLabel[20];
    public JTextField idSeqTdvTF;
    public JTextField horaDeSaidaTF;
    public JTextField numVagasDisponiveisTF;
    public JTextField horaDeChegadaTF;
    public JTextField idSeqCarroTF;
    public JTextField idSeqMotoristaTF;
    protected JButton cadastrarIdvButton;
    protected JButton updateIdvButton;
    protected JButton deleteIdvButton;
    protected JButton sair;
    protected JTextArea observacaoTA;
    protected JComboBox escolhaDeDia;
    protected JComboBox escolhaDeMes;
    protected JComboBox escolhaDeAno;
    protected int tipoOperacao = 0;
    public DataBaseManagerImpl dbm;

    //protected Filme filmeCriado;
    public CadastraInstanciaDeViagem(int tipoOperacao,DataBaseManagerImpl dbm) {

        //--Informa��es da Janela--
        super("Cadastrar Instancia De Viagem");
        setLayout(null);
        setSize(440, 380);
        setResizable(false);
        setLocation(250, 100);
        //---------------------

        this.dbm = dbm;

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

        //--Vagas Disponiveis--
        rotulos[1] = new JLabel("Vagas Disponiveis");
        rotulos[1].setFont(padraoParaRotulos);
        rotulos[1].setBounds(10, 60, 180, 20);
        add(rotulos[1]);

        numVagasDisponiveisTF = new JTextField("");
        numVagasDisponiveisTF.setFont(padrao);
        numVagasDisponiveisTF.setBounds(10, 80, 20, 20);
        numVagasDisponiveisTF.setEditable(false);
        add(numVagasDisponiveisTF);
        //---------------------

        //--Campo Hora De Saida--
        rotulos[2] = new JLabel("Hora De Saida");
        rotulos[2].setFont(padraoParaRotulos);
        rotulos[2].setBounds(160, 60, 150, 20);
        add(rotulos[2]);

        horaDeSaidaTF = new JTextField("");
        horaDeSaidaTF.setFont(padrao);
        horaDeSaidaTF.setBounds(160, 80, 50, 20);
        add(horaDeSaidaTF);
        //---------------------

        //--Hora De Chegada--
        rotulos[3] = new JLabel("Hora De Chegada");
        rotulos[3].setFont(padraoParaRotulos);
        rotulos[3].setBounds(300, 60, 150, 20);
        add(rotulos[3]);

        horaDeChegadaTF = new JTextField("", 5);
        horaDeChegadaTF.setFont(padrao);
        horaDeChegadaTF.setBounds(300, 80, 50, 20);
        add(horaDeChegadaTF);
        //---------------------


        //--observacoes--
        rotulos[4] = new JLabel("Observacoes:");
        rotulos[4].setFont(padraoParaRotulos);
        rotulos[4].setBounds(250, 150, 120, 20);
        add(rotulos[4]);

        observacaoTA = new JTextArea();
        //observacaoTA.setText(f.retornaobservacaoTA());
        //observacaoTA.setBackground(this.getBackground());
        observacaoTA.setBounds(200, 170, 200, 100);
        //observacaoTA.setEditable(false);
        //observacaoTA.setEnabled(false);
        add(observacaoTA);

        //data
        rotulos[5] = new JLabel("Data");
        rotulos[5].setFont(padraoParaRotulos);
        rotulos[5].setBounds(10, 110, 75, 20);
        add(rotulos[5]);

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


        //--Id. Seq. Carro--
        rotulos[6] = new JLabel("Id. Seq. Carro");
        rotulos[6].setFont(padraoParaRotulos);
        rotulos[6].setBounds(10, 250, 150, 20);
        add(rotulos[6]);

        idSeqCarroTF = new JTextField("");
        idSeqCarroTF.setFont(padrao);
        idSeqCarroTF.setBounds(10, 270, 20, 20);
        add(idSeqCarroTF);
        //--------------------

        //--Id. Seq. Rodoviaria Partida--
        rotulos[7] = new JLabel("Id. Seq. Motorista");
        rotulos[7].setFont(padraoParaRotulos);
        rotulos[7].setBounds(10, 290, 155, 20);
        add(rotulos[7]);

        idSeqMotoristaTF = new JTextField("");
        idSeqMotoristaTF.setFont(padrao);
        idSeqMotoristaTF.setBounds(10, 310, 30, 20);
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
                dbm.updateInstanciaDeViagem(idv.getIdSeqTdv(),
                		idv.getData(),idv);
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
        
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        //Date aux = (Date)formatter.parse("01/29/02");  
        idv.setData((Date)formatter.parse((String)escolhaDeDia.getSelectedItem()
        		+ (String)escolhaDeMes.getSelectedItem()
        		+ (String)escolhaDeAno.getSelectedItem()));
        		
        idv.setNumVagasDisponiveis(Integer.parseInt(numVagasDisponiveisTF.getText()));
        idv.setHoraRealSaida(horaDeSaidaTF.getText());
        idv.setHoraRealChegada(horaDeChegadaTF.getText());
        idv.setIdSeqCarro(Integer.parseInt(idSeqCarroTF.getText()));
        idv.setIdSeqMotorista(Integer.parseInt(idSeqMotoristaTF.getText()));
        idv.setObservacoes(observacaoTA.getText());
        
        return idv;
    }
}
