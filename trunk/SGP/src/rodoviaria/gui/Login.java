package rodoviaria.gui;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import DatabaseManager.ConexaoBanco;
import MainGui.TestaMenuPrincipal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//classe para proteger dados de nao-funcionarios
public class Login extends JDialog {

    //objetos utilizados no JFrame
    String array[] = new String[0];
    private JLabel nome, senha;
    private JTextField seuNome;
    private JPasswordField suaSenha;
    private JButton ok, sair;
    //campo auxiliar
    private String senhaDigitada;

    //construtor
    public Login(JFrame frame, String texto, boolean bool) {

        super(frame, texto, bool);

        setLayout(null);

        //texto exibindo "nome:"
        nome = new JLabel("nome:");
        nome.setBounds(55, 42, 47, 16);
        add(nome);

        //espaco para inserir o nome
        seuNome = new JTextField();
        seuNome.setBounds(103, 39, 91, 20);
        add(seuNome);

        //texto exibindo "senha:"
        senha = new JLabel("senha:");
        senha.setBounds(56, 79, 45, 16);
        add(senha);

        //espaco para inserir a senha
        suaSenha = new JPasswordField();
        suaSenha.setBounds(103, 77, 91, 20);
        add(suaSenha);

        //botao "ok"
        ok = new JButton("ok");
        ok.setBounds(224, 35, 70, 20);
        add(ok);

        //botao para sair do programa
        sair = new JButton();
        sair.setBounds(224, 80, 70, 20);
        Icon x = new ImageIcon(getClass().getResource(
                "publish_x.png"));
        sair.setIcon(x);
        add(sair);

        //eventos
        LoginHandler handler = new LoginHandler();

        seuNome.addActionListener(handler);
        suaSenha.addActionListener(handler);
        ok.addActionListener(handler);
        sair.addActionListener(handler);

    }

    //classe interna
    private class LoginHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                //se apertar enter nos textos ou apertar no botao
                if (event.getSource() == seuNome || event.getSource() == suaSenha || event.getSource() == ok) {

                    //se ambos os campos estiverem corretos
                    senhaDigitada = new String(suaSenha.getPassword());
                    if (autentica(seuNome.getText(), senhaDigitada) == true) {
                        dispose();
                        TestaMenuPrincipal.main(array);
                    } // se algum campo estiver errado
                    else {
                        JOptionPane.showMessageDialog(Login.this, "nome ou senha invalidos", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                } //se apertar em sair
                else if (event.getSource() == sair) {
                    ConexaoBanco.closeConnection(ConexaoBanco.getConnection());
                    System.exit(0);
                } //se for mudar senha chamar o outro JFrame
               
            } catch (Exception e) {
            }

        }
    }

    public boolean autentica(String nomeDigitado, String senhaDigitada) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = ConexaoBanco.getConnection();

        try {

            stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery("select senha from agente WHERE usuario = '" + nomeDigitado + "' ORDER BY usuario");

            if (!resultSet.next()) {
                return false;
            }

            String senhaBanco = resultSet.getString("senha");

            if (senhaBanco.compareTo(senhaDigitada) == 0) {
                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }
}
