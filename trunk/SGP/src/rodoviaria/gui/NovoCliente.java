package rodoviaria.gui;

import util.JNumericField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import cliente.Cliente;
import java.rmi.RemoteException;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
//import nucleo_administracao.database.DataBaseManager;
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
    public static final int COMPONENT_HEIGHT  = 40;

    public final DataBaseManager dbm;

    public NovoCliente(final DataBaseManager dbm) {

        super("Novo Passageiro");
        setBounds(400, 100, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        this.dbm = dbm;

        nomeTF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL,
                LARGURA - (2 * MARGEM_HORIZONTAL), COMPONENT_HEIGHT);
        nomeTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Nome *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(nomeTF);
        
        logradouroTF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + LINHA,
                300, COMPONENT_HEIGHT);
        logradouroTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Logradouro", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(logradouroTF);

        bairroTF.setBounds
                (MARGEM_HORIZONTAL + 305, MARGEM_VERTICAL + LINHA,
                LARGURA - (2 * MARGEM_HORIZONTAL) - 305, COMPONENT_HEIGHT);
        bairroTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Bairro", 0, 0, new Font("Tahoma", 0, 10)
                )
        );

        numeroNF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (2 * LINHA),
                75, COMPONENT_HEIGHT);
        numeroNF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Numero", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(numeroNF);

        complementoTF.setBounds
                (MARGEM_HORIZONTAL + 85, MARGEM_VERTICAL + (2 * LINHA),
                LARGURA - (2 * MARGEM_HORIZONTAL) - 85, COMPONENT_HEIGHT);
        complementoTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Complemento", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(complementoTF);

        add(bairroTF);

        cidadeTF.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (3 * LINHA),
                MEIO - MARGEM_HORIZONTAL - 5, COMPONENT_HEIGHT);
        cidadeTF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Cidade", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(cidadeTF);

        estadoCB.setBounds
                (MEIO + 5, MARGEM_VERTICAL + (3 * LINHA) + 5,
                50, 30);
        add(estadoCB);

        cepNF.setBounds
                (MEIO + 65, MARGEM_VERTICAL + (3 * LINHA),
                LARGURA - MEIO - MARGEM_HORIZONTAL - 65, COMPONENT_HEIGHT);
        cepNF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "CEP", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(cepNF);

        JLabel sexo = new JLabel("sexo *");
        sexo.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (4 * LINHA) - 7,
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

        cpfNF.setBounds
                (MARGEM_HORIZONTAL + 100, MARGEM_VERTICAL + (4 * LINHA) + 10,
                225, COMPONENT_HEIGHT);
        cpfNF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "CPF", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(cpfNF);

        estudanteChB.setBounds
                (MARGEM_HORIZONTAL + 350, MARGEM_VERTICAL + (4 * LINHA) + 10,
                LARGURA - 2 * MARGEM_HORIZONTAL - 280, COMPONENT_HEIGHT);
        add(estudanteChB);

        JLabel nasc = new JLabel("Data de nascimento *");
        nasc.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (5 * LINHA) + 10,
                120, 20);
        add(nasc);

        diaNascimentoCB.setBounds
                (MARGEM_HORIZONTAL, MARGEM_VERTICAL + (5 * LINHA) + 30,
                50, 27);
        add(diaNascimentoCB);
        mesNascimentoCB.setBounds
                (MARGEM_HORIZONTAL + 55, MARGEM_VERTICAL + (5 * LINHA) + 30,
                125, 27);
        add(mesNascimentoCB);
        anoNascimentoNF.setBounds
                (MARGEM_HORIZONTAL + 185, MARGEM_VERTICAL + (5 * LINHA) + 30,
                50, 27);
        add(anoNascimentoNF);

        dddNF.setBounds
                (MEIO + 30, MARGEM_VERTICAL + (5 * LINHA) + 20,
                40, COMPONENT_HEIGHT);
        dddNF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "ddd", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(dddNF);

        telefoneNF.setBounds
                (MEIO + 75, MARGEM_VERTICAL + (5 * LINHA) + 20,
                LARGURA - MEIO - MARGEM_HORIZONTAL - 75, COMPONENT_HEIGHT);
        telefoneNF.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Telefone", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(telefoneNF);

        concluirB.setBounds(50, 400, 195, 50);
        add(concluirB);

        sairB.setBounds(255, 400, 195, 50);
        sairB.addActionListener(

            new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //dispose();
                    System.exit(0);
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

//                        System.out.println(novo.stringToQuery());

                        dbm.insertCliente(novo.stringToQuery());
                    
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
