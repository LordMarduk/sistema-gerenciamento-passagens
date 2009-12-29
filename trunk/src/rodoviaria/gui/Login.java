package rodoviaria.gui;

import rodoviaria.gui.cliente.GerenciamentoDeClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import util.DataBaseManager;

/*
 * @author Marcello Passos
 * @since 25 de dezembro de 2009
 * @description Classe que fornece uma interface para a validacao de acesso ao
 * programa no nucleo rodoviaria.
 * @in nome do usuario, senha
 * @out validacao ou nao validacao do acesso ao programa
 * @keywords validacao, acesso ao banco de dados, usuario, senha, agente, rodoviaria, cliente
 */
public class Login extends JFrame {

    private JTextField user = new JTextField();
    private JTextField password = new JPasswordField();
    private JButton confirmar = new JButton("Confirmar");
    private JButton cancelar = new JButton("Cancelar");
    public final DataBaseManager dbm;

    public Login(final DataBaseManager dbm) {

        super("Login");
        setBounds(450, 300, 400, 200);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.dbm = dbm;

        user.setBounds(20, 25, 360, 25);
        password.setBounds(20, 75, 360, 25);
        confirmar.setBounds(20, 115, 175, 50);
        cancelar.setBounds(205, 115, 175, 50);

        JLabel l1 = new JLabel("Usuário");
        JLabel l2 = new JLabel("Senha");

        l1.setBounds(20, 5, 100, 20);
        l2.setBounds(20, 55, 100, 20);

        ConfirmarHandler ch = new ConfirmarHandler();

        confirmar.addActionListener( ch );

        cancelar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );

        password.addActionListener( ch );

        add(l1);
        add(l2);

        add(user);
        add(password);
        add(confirmar);
        add(cancelar);

        repaint();

    }

    public class ConfirmarHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                if (user.getText().equals("") || password.getText().equals("")) {
                    return;
                }
                boolean entradaValida =
                        dbm.validaEntradaAgente(user.getText(), password.getText());
                if (entradaValida) {
                    /*
                     * to change
                     */
                      GerenciamentoDeClientes gcframe = new GerenciamentoDeClientes(dbm);
                    /*
                     * 
                     */
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.", "Erro!", 0, null);
                    user.setText("");
                    password.setText("");
                    repaint();
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problemas ao conectar-se com o servidor.", "Erro!", 0, null);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problemas ao conectar-se com o servidor.", "Erro!", 0, null);
            }
        }

    }
    
}