package rodoviaria.gui.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import rodoviaria.cliente.Cliente;
import util.Auxiliares;
import util.DataBaseManager;
import util.Date;
import util.JNumericField;

public class EditarCliente extends JFrame{
    
    final DataBaseManager dbm;
    private final Cliente cliente;

    private JTextField id = new JTextField();
    private JTextField nome = new JTextField();
    private JTextField endereco = new JTextField();
    private JTextField cpf = new JNumericField(11);
    private JTextField ddd = new JNumericField(2);
    private JTextField telefone = new JNumericField(8);

    private JCheckBox estudanteChB = new JCheckBox("estudante");

    private JRadioButton masculinoRB;
    private JRadioButton femininoRB;
    private ButtonGroup sexoBG = new ButtonGroup();

    private JComboBox diaNascimentoCB = new JComboBox(Auxiliares.DIAS);
    private JComboBox mesNascimentoCB = new JComboBox(Auxiliares.MESES);
    private JNumericField anoNascimentoNF = new JNumericField(4);

    private JButton concluirB = new JButton("Concluir");
    private JButton sairB     = new JButton("Sair");


    public EditarCliente(final Cliente cliente, final DataBaseManager dbm){

        super("Editar Cliente : " + cliente.getId() + " - " + cliente.getNome());
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setBounds(400, 100, 500, 380);
        setLayout(null);

        this.dbm = dbm;
        this.cliente = cliente;

        JLabel idl = new JLabel("ID");
        idl.setBounds(20, 10, 50, 20);
        add(idl);
        id.setBounds(20, 30, 75, 30);
        id.setText(cliente.getId() + "");
        id.setEditable(false);

        JLabel nomel = new JLabel("Nome");
        nomel.setBounds(100, 10, 100, 20);
        add(nomel);
        nome.setBounds(100, 30, 380, 30);
        nome.setText(cliente.getNome());

        JLabel enderecol = new JLabel("Endereço");
        enderecol.setBounds(20, 60, 100, 20);
        add(enderecol);
        endereco.setBounds(20, 80, 460, 30);
        endereco.setText(cliente.getEndereco());

        JLabel sexol = new JLabel("Sexo");
        sexol.setBounds(20, 115, 100, 20);
        add(sexol);
        masculinoRB = new JRadioButton("Masculino", cliente.getSexo() == 'M');
        masculinoRB.setBounds(20, 135, 100, 20);
        femininoRB = new JRadioButton("Feminino", cliente.getSexo() == 'F');
        femininoRB.setBounds(20, 160, 100, 20);
        sexoBG.add(masculinoRB);
        sexoBG.add(femininoRB);

        JLabel nascl = new JLabel("Data de nascimento");
        nascl.setBounds(125, 125, 150, 20);
        add(nascl);
        diaNascimentoCB.setBounds(125, 145, 50, 30);
        diaNascimentoCB.setSelectedIndex(cliente.getData_nascimento().d - 1);
        mesNascimentoCB.setBounds(180, 145, 125, 30);
        mesNascimentoCB.setSelectedIndex(cliente.getData_nascimento().m - 1);
        anoNascimentoNF.setBounds(315, 145, 50, 30);
        anoNascimentoNF.setText(cliente.getData_nascimento().a + "");

        JLabel cpfl = new JLabel("CPF");
        cpfl.setBounds(20, 200, 100, 20);
        add(cpfl);
        cpf.setBounds(20, 220, 200, 30);
        cpf.setText(cliente.getCpf());

        estudanteChB.setBounds(395, 145, 100, 30);
        estudanteChB.setSelected(cliente.isEEstudante());

        JLabel fonel = new JLabel("Telefone");
        fonel.setBounds(305, 200, 100, 20);
        add(fonel);
        ddd.setBounds(305, 220, 50, 30);
        ddd.setText(cliente.getTelefone().substring(0, 2));

        telefone.setBounds(365, 220, 115, 30);
        telefone.setText(cliente.getTelefone().substring(2));

        concluirB.setBounds(50, 280, 195, 50);
        concluirB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if( (nome.getText().equals(""))||(anoNascimentoNF.getText().equals("")) ){
                    JOptionPane.showMessageDialog(null, "Campos Obrigatórios não preenchidos1", "Erro", 0);
                    return;
                }
                cliente.setNome(nome.getText());
                cliente.setEndereco(endereco.getText());
                cliente.setTelefone(ddd.getText() + telefone.getText());
                cliente.setData_nascimento(
                    new Date(
                        diaNascimentoCB.getSelectedIndex() + 1,
                        mesNascimentoCB.getSelectedIndex() + 1,
                        Integer.parseInt(anoNascimentoNF.getText()))
                );
                cliente.setSexo(masculinoRB.isSelected() ? 'M' : 'F');
                cliente.setEEstudante(estudanteChB.isSelected() ? true : false);
                try {
                    dbm.updateCliente(cliente.getId(), cliente);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        sairB.setBounds(255, 280, 195, 50);
        sairB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    dispose();
            }
        });

        add(id);
        add(nome);
        add(endereco);
        add(masculinoRB);
        add(femininoRB);
        add(diaNascimentoCB);
        add(mesNascimentoCB);
        add(anoNascimentoNF);
        add(cpf);
        add(estudanteChB);
        add(ddd);
        add(telefone);
        add(concluirB);
        add(sairB);

        repaint();

    }

}
