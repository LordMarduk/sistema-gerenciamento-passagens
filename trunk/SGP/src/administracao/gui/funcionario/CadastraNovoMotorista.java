package administracao.gui.funcionario;

import administracao.database.DataBaseManagerImpl;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import util.Auxiliares;
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

    private JLabel dataNascimento = new JLabel("Data de Nascimento:");
    private JComboBox     diaNascimentoMotorista = new JComboBox(Auxiliares.DIAS);
    private JComboBox     mesNascimentoMotorista = new JComboBox(Auxiliares.MESES);
    private JNumericField anoNascimentoMotorista = new JNumericField(4);

    private JNumericField cpfMotorista = new JNumericField(11);

    private JTextField enderecoMotorista = new JTextField(300);

    private JNumericField telefoneMotorista = new JNumericField(8);

    private JNumericField cnhMotorista = new JNumericField(10);

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton apagar = new JButton("Apagar");
    private JButton sair = new JButton("Sair");

    public final DataBaseManagerImpl dbm;

    public CadastraNovoMotorista (final DataBaseManagerImpl dbm) {

        super("Cadastrar Motorista");

        setBounds(390,220,500,400);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;

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

        dataNascimento.setBounds(5,50,100,15);
        add(dataNascimento);

        diaNascimentoMotorista.setBounds(5,65,40,40);
        mesNascimentoMotorista.setBounds(50,65,100,40);
        anoNascimentoMotorista.setBounds(155,65,60,40);
        anoNascimentoMotorista.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Ano", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(diaNascimentoMotorista);
        add(mesNascimentoMotorista);
        add(anoNascimentoMotorista);

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
                    null, "Endereço", 0, 0, new Font("Tahoma", 0, 10)
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
        add(cadastrar);

        atualizar.setBounds(245,250,130,50);
        add(atualizar);

        apagar.setBounds(110,305,130,50);
        add(apagar);

        sair.setBounds(245,305,130,50);
        add(sair);

    }


}