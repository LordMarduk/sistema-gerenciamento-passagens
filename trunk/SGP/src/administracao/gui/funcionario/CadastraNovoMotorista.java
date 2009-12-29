package administracao.gui.funcionario;

import administracao.database.DataBaseManagerImpl;
import administracao.funcionario.Funcionario;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import util.JNumericField;

/**
 *
 * @author Ronei
 */
public class CadastraNovoMotorista extends JFrame {

    private JTextField nomeMotorista = new JTextField(100);

    private JLabel sexo = new JLabel("sexo:");
    private JRadioButton masculinoRB = new JRadioButton("Masculino", false);
    private JRadioButton femininoRB = new JRadioButton("Feminino", false);
    private ButtonGroup  sexoMotorista     = new ButtonGroup();

    private JFormattedTextField data_nascimento;

    private JNumericField cpfMotorista = new JNumericField(11);

    private JTextField enderecoMotorista = new JTextField(300);

    private JNumericField telefoneMotorista = new JNumericField(8);

    private JNumericField cnhMotorista = new JNumericField(10);

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton apagar = new JButton("Apagar");
    private JButton sair = new JButton("Sair");

    public final DataBaseManagerImpl dbm;
    MaskFormatter formatter;
    int flagEnableButton = 0;


    public CadastraNovoMotorista (final DataBaseManagerImpl dbm, int flagEnableButton) {

        super("Cadastrar Motorista");

        setBounds(390,220,500,400);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;
        this.flagEnableButton = flagEnableButton;

        //-----

        nomeMotorista.setBounds(5,5,350,40);
        nomeMotorista.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Nome *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(nomeMotorista);

        sexo.setBounds(360,2,135,10);
        add(sexo);

        masculinoRB.setBounds(365,15,140,15);
        femininoRB.setBounds(365,30,140,15);
        sexoMotorista.add(masculinoRB);
        sexoMotorista.add(femininoRB);
        add(masculinoRB);
        add(femininoRB);

        try {
            formatter = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        data_nascimento = new javax.swing.JFormattedTextField(formatter);

        data_nascimento.setBounds(5,65,200,40);
        data_nascimento.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Data Nascimento", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(data_nascimento);

        cpfMotorista.setBounds(220,65,255,40);
        cpfMotorista.setBorder(
                BorderFactory.createTitledBorder(
                    null, "CPF *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(cpfMotorista);

        enderecoMotorista.setBounds(5,110,470,40);
        enderecoMotorista.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Endereço", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(enderecoMotorista);

        telefoneMotorista.setBounds(5,155,270,40);
        telefoneMotorista.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Telefone", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(telefoneMotorista);

        cnhMotorista.setBounds(280,155,195,40);
        cnhMotorista.setBorder(
                BorderFactory.createTitledBorder(
                    null, "CNH *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(cnhMotorista);

        cadastrar.setBounds(110,250,130,50);
            if(flagEnableButton == 1)
                cadastrar.setEnabled(false);
        cadastrar.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {
                        try {
                            Funcionario fun = setarEmObjetos();
                            dbm.insertMotorista(fun);

                        } catch (Exception e) {
                        }
                        dispose();
                    }
                }
        );
        add(cadastrar);

        atualizar.setBounds(245,250,130,50);
            if(flagEnableButton == 0)
                atualizar.setEnabled(false);
        atualizar.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {
                        try {
                            Funcionario fun = setarEmObjetos();
                            dbm.updateMotorista(fun.getIdSeqFuncionario(), fun);

                        } catch (Exception e) {
                        }
                        dispose();
                    }
                }
        );
        add(atualizar);

        apagar.setBounds(110,305,130,50);
            if(flagEnableButton == 0)
                apagar.setEnabled(false);
        apagar.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {
                        try {
                            Funcionario fun = setarEmObjetos();
                            dbm.deleteMotorista(fun.getIdSeqFuncionario());

                        } catch (Exception e) {
                        }
                        dispose();
                    }
                }
        );
        add(apagar);

        sair.setBounds(245,305,130,50);
        sair.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {
                        dispose();
                    }
                }
        );
        add(sair);

    }
    
    public Funcionario setarEmObjetos() throws Exception {

        Funcionario novo = new Funcionario();

            char sex = masculinoRB.isSelected() ? 'M' : 'F';

            novo.setNome(nomeMotorista.getText());
            novo.setCpf(Long.parseLong(cpfMotorista.getText()));
            novo.setEndereco(enderecoMotorista.getText());
            novo.setTelefone(Long.parseLong(telefoneMotorista.getText()));
            novo.setSexo(Character.toString(sex));
            novo.setDatanascimento(data_nascimento.getText());
            novo.setCnh(Long.parseLong(cnhMotorista.getText()));

        return novo;
    }


}