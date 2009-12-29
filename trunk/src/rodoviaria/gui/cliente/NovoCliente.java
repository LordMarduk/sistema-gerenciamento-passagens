package rodoviaria.gui.cliente;

import util.JNumericField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rodoviaria.cliente.Cliente;
import java.rmi.RemoteException;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import util.Auxiliares;
import util.DataBaseManager;

public class NovoCliente extends JFrame {

    private Cliente novo;

    private JTextField nomeTF = new JTextField(100);

    private JRadioButton masculinoRB = new JRadioButton("Masculino", false);
    private JRadioButton femininoRB = new JRadioButton("Feminino", false);
    private ButtonGroup  sexoBG     = new ButtonGroup();

    private JNumericField cpfNF = new JNumericField(11);

    private JComboBox     diaNascimentoCB = new JComboBox(Auxiliares.DIAS);
    private JComboBox     mesNascimentoCB = new JComboBox(Auxiliares.MESES);
    private JNumericField anoNascimentoNF = new JNumericField(4);

    private JTextField    logradouroTF  = new JTextField(100);
    private JTextField    complementoTF = new JTextField(100);
    private JTextField    bairroTF      = new JTextField(100);
    private JTextField    cidadeTF      = new JTextField(100);
    private JNumericField numeroNF      = new JNumericField(4);
    private JNumericField cepNF         = new JNumericField(8);
    private JComboBox     estadoCB      = new JComboBox(Auxiliares.UFS);

    private JNumericField dddNF      = new JNumericField(2);
    private JNumericField telefoneNF = new JNumericField(8);
    
    private JCheckBox     estudanteChB = new JCheckBox("estudante");

    private JButton       concluirB = new JButton("Concluir");
    private JButton       sairB     = new JButton("Sair");

    public static final int MARGEM_HORIZONTAL = 30;
    public static final int MARGEM_VERTICAL   = 30;
    public static final int LARGURA           = 500;
    public static final int ALTURA            = 500;
    public static final int LINHA             = 50;
    public static final int MEIO              = 250;
    public static final int COMPONENT_HEIGHT  = 28;

    public final DataBaseManager dbm;

    public NovoCliente(final DataBaseManager dbm) {

        super("Novo Passageiro");
        setBounds(400, 100, 500, 500);
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setLayout(null);

        this.dbm = dbm;

        JLabel nomeLabel = new JLabel("Nome*");
        nomeLabel.setBounds(MARGEM_HORIZONTAL, 10, 100, 20);
        add(nomeLabel);
        nomeTF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL,
                LARGURA - (2 * MARGEM_HORIZONTAL), COMPONENT_HEIGHT);
        add(nomeTF);

        JLabel logradouroLabel = new JLabel("Logradouro");
        logradouroLabel.setBounds(MARGEM_HORIZONTAL, 60, 100, 20);
        add(logradouroLabel);
        logradouroTF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + LINHA,
                300, COMPONENT_HEIGHT);
        add(logradouroTF);

        JLabel bairroLabel = new JLabel("Bairro");
        bairroLabel.setBounds(MARGEM_HORIZONTAL + 305, 60, 100, 20);
        add(bairroLabel);
        bairroTF.setBounds
                (MARGEM_HORIZONTAL + 305, MARGEM_VERTICAL + LINHA,
                LARGURA - (2 * MARGEM_HORIZONTAL) - 305, COMPONENT_HEIGHT);
        add(bairroTF);

        JLabel numeroLabel = new JLabel("Número");
        numeroLabel.setBounds(MARGEM_HORIZONTAL, 110, 100, 20);
        add(numeroLabel);
        numeroNF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (2 * LINHA),
                75, COMPONENT_HEIGHT);
        add(numeroNF);

        JLabel complLabel = new JLabel("Complemento");
        complLabel.setBounds(MARGEM_HORIZONTAL + 85, 110, 100, 20);
        add(complLabel);
        complementoTF.setBounds
                (MARGEM_HORIZONTAL + 85, MARGEM_VERTICAL + (2 * LINHA),
                LARGURA - (2 * MARGEM_HORIZONTAL) - 85, COMPONENT_HEIGHT);
        add(complementoTF);

        JLabel cidadeLabel = new JLabel("Cidade");
        cidadeLabel.setBounds(MARGEM_HORIZONTAL, 160, 100, 20);
        add(cidadeLabel);
        cidadeTF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (3 * LINHA),
                MEIO - MARGEM_HORIZONTAL - 5, COMPONENT_HEIGHT);
        add(cidadeTF);

        JLabel ufLabel = new JLabel("UF");
        ufLabel.setBounds(MEIO + 5, 160, 30, 20);
        add(ufLabel);
        estadoCB.setBounds
                (MEIO + 5, MARGEM_VERTICAL + (3 * LINHA) - 1,
                50, COMPONENT_HEIGHT + 2);
        add(estadoCB);

        JLabel cepLabel = new JLabel("CEP");
        cepLabel.setBounds(MEIO + 65, 160, 100, 20);
        add(cepLabel);
        cepNF.setBounds
                (MEIO + 65, MARGEM_VERTICAL + (3 * LINHA),
                LARGURA - MEIO - MARGEM_HORIZONTAL - 65, COMPONENT_HEIGHT);
        add(cepNF);

        JLabel sexo = new JLabel("Sexo*");
        sexo.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (4 * LINHA) - 10,
                50, 20);
        add(sexo);

        masculinoRB.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (4 * LINHA) + 10,
                100, 20);
        femininoRB.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (4 * LINHA) + 30,
                100, 20);
        sexoBG.add(masculinoRB);
        sexoBG.add(femininoRB);
        add(masculinoRB);
        add(femininoRB);

        JLabel cpfLabel = new JLabel("CPF");
        cpfLabel.setBounds(130, 220, 100, 20);
        add(cpfLabel);
        cpfNF.setBounds
                (MARGEM_HORIZONTAL + 100, MARGEM_VERTICAL + (4 * LINHA) + 10,
                225, COMPONENT_HEIGHT);
        add(cpfNF);

        estudanteChB.setBounds
                (MARGEM_HORIZONTAL + 350, MARGEM_VERTICAL + (4 * LINHA) + 10,
                LARGURA - 2 * MARGEM_HORIZONTAL - 280, COMPONENT_HEIGHT);
        add(estudanteChB);

        JLabel nasc = new JLabel("Data de nascimento*");
        nasc.setBounds(MARGEM_HORIZONTAL, MARGEM_VERTICAL + (5 * LINHA) + 10, 120, 20);
        add(nasc);
        diaNascimentoCB.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (5 * LINHA) + 30,
                50, COMPONENT_HEIGHT + 2);
        add(diaNascimentoCB);
        mesNascimentoCB.setBounds
                (MARGEM_HORIZONTAL + 55, MARGEM_VERTICAL + (5 * LINHA) + 30,
                125, COMPONENT_HEIGHT + 2);
        add(mesNascimentoCB);
        anoNascimentoNF.setBounds
                (MARGEM_HORIZONTAL + 185, MARGEM_VERTICAL + (5 * LINHA) + 30,
                50, COMPONENT_HEIGHT + 2);
        add(anoNascimentoNF);

        JLabel dddLabel = new JLabel("DDD");
        dddLabel.setBounds(MEIO + 30, 290, 30, 20);
        add(dddLabel);
        dddNF.setBounds
                (MEIO + 30, MARGEM_VERTICAL + (5 * LINHA) + 30,
                40, COMPONENT_HEIGHT + 2);
        add(dddNF);

        JLabel telLabel = new JLabel("Telefone");
        telLabel.setBounds(MEIO + 75, 290, 100, 20);
        add(telLabel);
        telefoneNF.setBounds
                (MEIO + 75, MARGEM_VERTICAL + (5 * LINHA) + 30,
                LARGURA - MEIO - MARGEM_HORIZONTAL - 75, COMPONENT_HEIGHT + 2);
        add(telefoneNF);

        concluirB.setBounds(50, 400, 195, 50);
        add(concluirB);

        sairB.setBounds(255, 400, 195, 50);
        sairB.addActionListener(

            new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    dispose();
                }

            }

        );
        add(sairB);

        concluirB.setBounds(50, 400, 195, 50);
        concluirB.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String nom = nomeTF.getText();
                    String cpf = cpfNF.getText();

                    String tel = dddNF.getText() + telefoneNF.getText();
                    char sex = masculinoRB.isSelected() ? 'M' : 'F';
                    boolean est = estudanteChB.isSelected();

                    int d = diaNascimentoCB.getSelectedIndex() + 1;
                    int m = mesNascimentoCB.getSelectedIndex() + 1;
                    int a;
                    try{
                        a = Integer.parseInt( anoNascimentoNF.getText() );
                    }
                    catch(NumberFormatException ex){
                        a = 0;
                    }
                    Date dat = new Date(d, m, a);

                    String log = logradouroTF.getText();
                    int num;
                    try{
                        num = Integer.parseInt( numeroNF.getText() );
                    }
                    catch(NumberFormatException ex){
                        num = 0;
                    }
                    String com = complementoTF.getText();
                    String bai = bairroTF.getText();
                    String cid = cidadeTF.getText();
                    String uf = Auxiliares.UFS[ estadoCB.getSelectedIndex() ];
                    String cep = cepNF.getText();

                    if(nom.length() < 1){
                        JOptionPane.showMessageDialog
                                (null, "O campo nome é obrigatório!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(a < 1900){
                         JOptionPane.showMessageDialog
                                (null, "Ano inválido!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(cpf.length() != 11){
                        if(cpf.length() != 0){
                            JOptionPane.showMessageDialog
                                (null, "O campo CPF deve conter 11 digitos!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    if(cep.length() != 8){
                        if(cep.length() != 0){
                            JOptionPane.showMessageDialog
                                (null, "O campo CEP deve conter 8 digitos!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    if(dddNF.getText().length() != 2){
                        if(dddNF.getText().length() != 0){
                            JOptionPane.showMessageDialog
                                (null, "O campo ddd deve conter 2 digitos!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    if(telefoneNF.getText().length() != 8){
                        if(telefoneNF.getText().length() != 0){
                            JOptionPane.showMessageDialog
                                (null, "O campo telefone deve conter 8 digitos!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    if((!masculinoRB.isSelected())&&(!femininoRB.isSelected())){
                            JOptionPane.showMessageDialog
                                (null, "Um sexo deve ser escolhido!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                    }

//                    DataBaseManager dbm = new DataBaseManager();

                    try{

                        int cod = Auxiliares.gerarId( dbm.maximunValueCliente() );

                        String end = log + ", " + num + ", " + com + ", " + bai +
                                ", " + cid + ", " + uf;

                        novo = new Cliente(cod, nom, sex, dat, cpf, end, tel, est);

                        dbm.insertCliente(novo);
                    
                        dbm.closeConnection();

                    }
                    catch(RemoteException ex){
                        ex.printStackTrace();
                    }

                    dispose();
                    
                }
            }
        );
        add(concluirB);

        repaint();

    }

}
