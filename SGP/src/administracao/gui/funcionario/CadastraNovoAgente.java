package administracao.gui.funcionario;

import administracao.database.DataBaseManagerImpl;
import administracao.funcionario.Funcionario;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import util.JNumericField;

/**
 *
 * @author Ronei
 */
public class CadastraNovoAgente extends JFrame {

    protected JTextField nomeAgente = new JTextField(100);

    protected JTextField identificadorIdSeq = new JTextField(3);

    protected JLabel sexo = new JLabel("sexo* :");
    protected JRadioButton masculinoRB = new JRadioButton("Masculino", false);
    protected JRadioButton femininoRB = new JRadioButton("Feminino", false);
    protected ButtonGroup  sexoAgente     = new ButtonGroup();

    protected JNumericField cpfAgente = new JNumericField(11);

    protected JTextField enderecoAgente = new JTextField(300);

    protected JNumericField telefoneAgente = new JNumericField(8);

    protected JTextField usuarioAgente = new JTextField(10);

    protected JPasswordField senhaAgente = new JPasswordField(10);
    protected JPasswordField senhaAgente2 = new JPasswordField(10);

    protected JFormattedTextField data_nascimento;

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton apagar = new JButton("Apagar");
    private JButton sair = new JButton("Sair");

    MaskFormatter formatter = null;
    int flagEnableButton = 0;

    public final DataBaseManagerImpl dbm;

    public CadastraNovoAgente (final DataBaseManagerImpl dbm, int flagEnableButton) {

        super("Cadastrar Agente");

        setBounds(390,220,500,400);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;
        this.flagEnableButton = flagEnableButton;

        //-----

        identificadorIdSeq.setVisible(false);

        nomeAgente.setBounds(5,5,350,40);
        nomeAgente.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Nome *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(nomeAgente);

        sexo.setBounds(360,2,135,10);
        add(sexo);

        masculinoRB.setBounds(365,15,140,15);
        femininoRB.setBounds(365,30,140,15);
        sexoAgente.add(masculinoRB);
        sexoAgente.add(femininoRB);
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
                    null, "Data Nascimento *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(data_nascimento);
        
        cpfAgente.setBounds(220,65,255,40);
        cpfAgente.setBorder(
                BorderFactory.createTitledBorder(
                    null, "CPF *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(cpfAgente);

        enderecoAgente.setBounds(5,110,470,40);
        enderecoAgente.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Endereço", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(enderecoAgente);

        telefoneAgente.setBounds(5,155,270,40);
        telefoneAgente.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Telefone", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(telefoneAgente);

        usuarioAgente.setBounds(280,155,195,40);
        usuarioAgente.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Usuario *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(usuarioAgente);

        senhaAgente.setBounds(5,200,230,40);
        senhaAgente.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Senha *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(senhaAgente);

        senhaAgente2.setBounds(245,200,230,40);
        senhaAgente2.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Confirmar Senha *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(senhaAgente2);

        cadastrar.setBounds(110,250,130,50);
            if(flagEnableButton == 1)
                cadastrar.setEnabled(false);
        cadastrar.addActionListener(
                new ActionListener() {
                    // passa consulta para modelo de tabela

                    public void actionPerformed(ActionEvent event) {

                        //tratamento campo nome
                        if(nomeAgente.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                                  null,
                                  "Campo Nome Obrigatório",
                                  "Erro",
                                  JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }                        

                        //Tratamento Sexo
                        if((!masculinoRB.isSelected())&&(!femininoRB.isSelected())){
                            JOptionPane.showMessageDialog
                                (null, "Um sexo deve ser escolhido!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //tratamento data nascimento
                        Pattern p = Pattern.compile("[0]{1}[1-9]{1}[\\/][0]{1}[1-9]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[0]{1}[1-9]{1}[\\/][1]{1}[0-2]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[1-2]{1}[0-9]{1}[\\/][0]{1}[1-9]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[1-2]{1}[0-9]{1}[\\/][1]{1}[0-2]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[3]{1}[1]{1}[\\/][0]{1}[1-9]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}"+
                                                    "[3]{1}[1]{1}[\\/][1]{1}[0-2]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}");
                        Matcher m = p.matcher(data_nascimento.getText());
                        if(!(m.find())){
                            JOptionPane.showMessageDialog
                                (null, "Data Inválida!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //tratamento campo cpf
                        if(cpfAgente.getText().length() != 11){
                            JOptionPane.showMessageDialog(
                                  null,
                                  "CPF Inválido",
                                  "Erro",
                                  JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }

                        //tratamento usuario
                        if(usuarioAgente.getText().length() < 1){
                            JOptionPane.showMessageDialog
                                (null, "Usuario é Obrigatorio!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if(!(senhaAgente.getText().equals(senhaAgente2.getText())) || senhaAgente.getText().length() < 1){
                            JOptionPane.showMessageDialog
                                (null, "Senha Invalida!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        //tratamento telefone
                        if(telefoneAgente.getText().length() < 1){
                                                        
                            telefoneAgente.setText("00000000");
                            
                        }
                        
                        //tratamento endereco
                        if(enderecoAgente.getText().length() < 1){
                                                      
                            enderecoAgente.setText("não especificado");
                            
                        }                       
                        
                        try {

                            Funcionario fun = setarEmObjetos();
                            dbm.insertAgente(fun);

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

                        //tratamento campo nome
                        if(nomeAgente.getText().length() < 1){
                            JOptionPane.showMessageDialog(
                                  null,
                                  "Campo Nome Obrigatório",
                                  "Erro",
                                  JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }

                        //Tratamento Sexo
                        if((!masculinoRB.isSelected())&&(!femininoRB.isSelected())){
                            JOptionPane.showMessageDialog
                                (null, "Um sexo deve ser escolhido!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //tratamento data nascimento
                        Pattern p = Pattern.compile("[0]{1}[1-9]{1}[\\/][0]{1}[1-9]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[0]{1}[1-9]{1}[\\/][1]{1}[0-2]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[1-2]{1}[0-9]{1}[\\/][0]{1}[1-9]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[1-2]{1}[0-9]{1}[\\/][1]{1}[0-2]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}|"+
                                                    "[3]{1}[1]{1}[\\/][0]{1}[1-9]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}"+
                                                    "[3]{1}[1]{1}[\\/][1]{1}[0-2]{1}[\\/][1]{1}[9]{1}[0-9]{1}[0-9]{1}");
                        Matcher m = p.matcher(data_nascimento.getText());
                        if(!(m.find())){
                            JOptionPane.showMessageDialog
                                (null, "Data Inválida!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //tratamento campo cpf
                        if(cpfAgente.getText().length() != 11){
                            JOptionPane.showMessageDialog(
                                  null,
                                  "CPF Inválido",
                                  "Erro",
                                  JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }

                        //tratamento usuario
                        if(usuarioAgente.getText().length() < 1){
                            JOptionPane.showMessageDialog
                                (null, "Usuario é Obrigatorio!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if(!(senhaAgente.getText().equals(senhaAgente2.getText())) || senhaAgente.getText().length() < 1){
                            JOptionPane.showMessageDialog
                                (null, "Senha Invalida!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //tratamento telefone
                        if(telefoneAgente.getText().length() < 1){

                            telefoneAgente.setText("00000000");

                        }

                        //tratamento endereco
                        if(enderecoAgente.getText().length() < 1){

                            enderecoAgente.setText("não especificado");

                        }

                        try {
                            Funcionario fun = setarEmObjetos();
                            dbm.updateAgente(fun.getIdSeqFuncionario(), fun);

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
                            dbm.deleteAgente(fun.getIdSeqFuncionario());

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
            
            if(flagEnableButton == 1)
                novo.setIdSeqFuncionario(Integer.parseInt(identificadorIdSeq.getText()));
            
            novo.setNome(nomeAgente.getText());
            novo.setCpf(Long.parseLong(cpfAgente.getText()));
            novo.setEndereco(enderecoAgente.getText());
            novo.setTelefone(Long.parseLong(telefoneAgente.getText()));
            novo.setSexo(Character.toString(sex));
            novo.setDatanascimento(data_nascimento.getText());
            novo.setUsuario(usuarioAgente.getText());
            novo.setSenha(senhaAgente.getText());

        return novo;
    }

}
