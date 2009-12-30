package administracao.gui.carro;

import administracao.carro.Carro;
import administracao.database.DataBaseManagerImpl;
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
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class CadastraNovoCarro extends JFrame {

    protected JTextField identificadorIdSeq = new JTextField(3);

    JLabel arCondicionadoLabel = new JLabel("Ar Condicionado:");

    protected JRadioButton simRB = new JRadioButton("Sim", false);
    protected JRadioButton naoRB = new JRadioButton("Não", false);
    protected ButtonGroup  arCondicionado     = new ButtonGroup();

    JFormattedTextField placa_carro;

    JFormattedTextField chassis_carro;

    MaskFormatter formatter = null;
    MaskFormatter formatter2 = null;

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton apagar = new JButton("Apagar");
    private JButton sair = new JButton("Sair");

    public final DataBaseManagerImpl dbm;
    private String respAr;
    int flagEnableButton = 0;


    public CadastraNovoCarro(final DataBaseManagerImpl dbm, int flagEnableButton) {

        super("Cadastrar Carro");

        setBounds(390,220,500,400);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;

        this.flagEnableButton = flagEnableButton;

        //-----

        identificadorIdSeq.setVisible(false);

        arCondicionadoLabel.setBounds(5,5,350,40);
        add(arCondicionadoLabel);

        simRB.setBounds(365,15,140,15);
        naoRB.setBounds(365,30,140,15);
        arCondicionado.add(simRB);
        arCondicionado.add(naoRB);
        add(simRB);
        add(naoRB);

        try {
            formatter = new MaskFormatter("UUU-####");
            formatter2 = new MaskFormatter("AAAAAAAAAAAAAAAAA");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        placa_carro = new javax.swing.JFormattedTextField(formatter);

        placa_carro.setBounds(5,65,200,40);
        placa_carro.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Placa Carro *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(placa_carro);

        chassis_carro = new javax.swing.JFormattedTextField(formatter2);
        chassis_carro.setBounds(220,65,255,40);
        chassis_carro.setBorder(
                BorderFactory.createTitledBorder(
                    null, "Chassis *", 0, 0, new Font("Tahoma", 0, 10)
                )
        );
        add(chassis_carro);

        cadastrar.setBounds(110,250,130,50);
            if(flagEnableButton == 1)
                cadastrar.setEnabled(false);
        cadastrar.addActionListener(
                new ActionListener() {
                   
                    public void actionPerformed(ActionEvent event) {

                       //Tratamento Ar Condicionado
                        if((!simRB.isSelected())&&(!naoRB.isSelected())){
                            JOptionPane.showMessageDialog
                                (null, "Defina se tem Ar Condicioando!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //tratamento campo placa
                        if(placa_carro.getText().length() != 8){
                            JOptionPane.showMessageDialog(
                                  null,
                                  "Placa Inválida!",
                                  "Erro",
                                  JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }

                        //tratamento campo chassis
                        if(chassis_carro.getText().length() != 17){
                            JOptionPane.showMessageDialog(
                                  null,"Chassis Inválido!","Erro",
                                  JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            Carro car = setarEmObjetos();
                            dbm.insertCarro(car);

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
                            Carro car = setarEmObjetos();
                            dbm.updateCarro(car.getId_seq_carro(), car);

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
                            Carro car = setarEmObjetos();
                            dbm.deleteCarro(car.getId_seq_carro());

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

    public Carro setarEmObjetos() throws Exception {

        Carro novo = new Carro();

        if(flagEnableButton == 1)
                novo.setId_seq_carro(Integer.parseInt(identificadorIdSeq.getText()));

        char sex = simRB.isSelected() ? 'S' : 'N';

        if(sex == 'S')
            respAr = "SIM";
        else
            respAr = "NAO";

        novo.setArCondicionado(respAr);
        novo.setPlaca(placa_carro.getText());
        novo.setChassis(chassis_carro.getText());


        return novo;
    }



}
