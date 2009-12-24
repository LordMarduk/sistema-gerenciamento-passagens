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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import administracao.database.DataBaseManagerImpl;
import javax.swing.JComboBox;
import util.Auxiliares;
import administracao.viagens.InstanciaDeViagem;

public class CadastraInstanciaDeViagem extends JFrame {

    protected Font padrao = new Font("Arial", Font.PLAIN, 12);
    protected Font padraoParaRotulos = new Font("Arial", Font.BOLD, 11);
    protected JLabel dataJL;
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
        setVisible(true);
        //---------------------

        this.dbm = dbm;

        this.tipoOperacao = tipoOperacao;

        idSeqTdvTF = new JTextField("");
        idSeqTdvTF.setFont(padrao);
        idSeqTdvTF.setBounds(10, 30, 150, 40);
        idSeqTdvTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Id. Seq. Tipo De Viagem", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(idSeqTdvTF);
        //--------------------

        //--Vagas Disponiveis--
        numVagasDisponiveisTF = new JTextField("");
        numVagasDisponiveisTF.setFont(padrao);
        numVagasDisponiveisTF.setBounds(10, 150, 20, 40);
        numVagasDisponiveisTF.setEditable(false);
        numVagasDisponiveisTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Numero De Vagas Disponiveis", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(numVagasDisponiveisTF);
        //---------------------

        //--Campo Hora De Saida--
        horaDeSaidaTF = new JTextField("");
        horaDeSaidaTF.setFont(padrao);
        horaDeSaidaTF.setBounds(160, 80, 120, 40);
        add(horaDeSaidaTF);
        horaDeSaidaTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Horario De Saida", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        horaDeChegadaTF = new JTextField("", 5);
        horaDeChegadaTF.setFont(padrao);
        horaDeChegadaTF.setBounds(300, 80, 120, 40);
        horaDeChegadaTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Horario De Chegada", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(horaDeChegadaTF);
        //---------------------
        //--observacoes--
      
        observacaoTA = new JTextArea();
        observacaoTA.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Observacoes:", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        //observacaoTA.setText(f.retornaobservacaoTA());
        //observacaoTA.setBackground(this.getBackground());
        observacaoTA.setBounds(200, 170, 200, 100);
        //observacaoTA.setEditable(false);
        //observacaoTA.setEnabled(false);
        add(observacaoTA);

        //data
        //dataJL = new JLabel("Data");
        //dataJL.setBounds(10, 110, 75, 20);
        //add(dataJL);

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
        idSeqCarroTF = new JTextField("");
        idSeqCarroTF.setFont(padrao);
        idSeqCarroTF.setBounds(10, 270, 120, 40);
        idSeqCarroTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Id. Seq. Carro", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        add(idSeqCarroTF);
        //--------------------
        //--Id. Seq. Rodoviaria Partida--
        
        idSeqMotoristaTF = new JTextField("");
        idSeqMotoristaTF.setFont(padrao);
        idSeqMotoristaTF.setBounds(10, 310, 120, 40);
        idSeqMotoristaTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Id. Seq. Motorista", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

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
