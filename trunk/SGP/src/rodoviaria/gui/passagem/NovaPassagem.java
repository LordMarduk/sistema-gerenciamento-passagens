package rodoviaria.gui.passagem;

import administracao.viagens.InstanciaDeViagem;
import administracao.viagens.TipoDeViagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rodoviaria.cliente.Cliente;
import rodoviaria.passagem.Passagem;
import util.Auxiliares;
import util.DataBaseManager;
import util.Date;
import util.JNumericField;
import util.QueryManager;

public class NovaPassagem extends JFrame{

    private final DataBaseManager dbm;
    public final QueryManager qm;

    private InstanciaDeViagem idv;

    private Passagem nova;

    private JTextField idTdvField = new JTextField();
    private JTextField dataIdvField = new JTextField();

    private JCheckBox segurado = new JCheckBox("Segurado");
    private JCheckBox estudante = new JCheckBox("Estudante");

    private JComboBox poltronasVagas;

    private JNumericField idClienteField = new JNumericField(5);

    private JButton concluir = new JButton("Concluir");
    private JButton sair = new JButton("Sair");

    private String codigoPassagem;
    private String nomePassageiro;
    private String cpfPassageiro;
    private String estud,seg;
    private String viagem,data,hora;
    private final JButton pegarIdCliente;

    public NovaPassagem(final InstanciaDeViagem idv,final DataBaseManager dbm,final QueryManager qm,final String viagem, final String data, final String hora){

        super("Nova Passagem");
        setBounds(500, 300, 280, 250);
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setLayout(null);

        this.dbm = dbm;
        this.qm =qm;
        this.idv = idv;
        this.viagem = viagem;
        this.data = data;
        this.hora = hora;

        JLabel l1 = new JLabel("ID - Tipo de Viagem");
        JLabel l2 = new JLabel("Data");
        JLabel l3 = new JLabel("Poltrona");
        JLabel l4 = new JLabel("ID - Cliente*");

        l1.setBounds(20, 10, 130, 20);
        l2.setBounds(150, 10, 100, 20);
        l3.setBounds(20, 100, 100, 20);
        l4.setBounds(150, 100, 100, 20);

        add(l1);
        add(l2);
        add(l3);
        add(l4);

        idTdvField.setText( idv.getIdSeqTdv() + "" );
        idTdvField.setEditable(false);
        idTdvField.setBounds(20, 30, 100, 25);
        add(idTdvField);

        dataIdvField.setText( idv.getData() );
        dataIdvField.setEditable(false);
        dataIdvField.setBounds(150, 30, 100, 25);
        add(dataIdvField);

        segurado.setBounds(20, 70, 100, 25);
        add(segurado);
        estudante.setBounds(150, 70, 100, 25);
        add(estudante);

        int[] pv = null;
        try {
            pv = dbm.getPoltronasVagas(idv);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        String[] pvs = new String[pv.length];
        for (int i = 0; i < pvs.length; i++)
            pvs[i] = pv[i] + "";

        poltronasVagas = new JComboBox( pvs );
        poltronasVagas.setBounds(20, 120, 100, 25);
        add(poltronasVagas);

        idClienteField.setBounds(150, 120, 100, 25);
        add(idClienteField);

        pegarIdCliente = new JButton("...");
        pegarIdCliente.setBounds(250, 125, 20, 20);
        pegarIdCliente.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        new SelecionarCliente(qm,idClienteField);
                    }
                }
        );
        add(pegarIdCliente);

        concluir.setBounds(20, 175, 100, 30);
        concluir.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if( idClienteField.getText().equals("") ){
                        JOptionPane.showMessageDialog(null,
                                "O campo ID - Cliente e obrigatorio!", "Erro", 0);
                        return;
                    }

                    int np = Integer.parseInt( poltronasVagas.getSelectedItem().toString() );
                    int idtdv = idv.getIdSeqTdv();
                    boolean se = segurado.isSelected();
                    boolean es = estudante.isSelected();
                    TipoDeViagem tdv = null;
                    try {
                        tdv = dbm.selectTipoDeViagem(idtdv);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }

                    Date dt = new Date( idv.getData() );
                    int cl = Integer.parseInt( idClienteField.getText() );

                    nova = new Passagem(np, se, es, tdv, dt, cl);

                    codigoPassagem = Auxiliares.gerarCodigoPassagem(np, idtdv, idv.getData());
                
                    //pegar dados
                        try {
                            Cliente cli = new Cliente();
                            cli = dbm.getCliente(cl);
                            nomePassageiro = cli.getNome();
                            cpfPassageiro = cli.getCpf();
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }


                        if(estudante.isSelected()){
                            estud = "sim";
                        }
                        else{
                            estud = "nao";
                        }

                        if(segurado.isSelected()){
                            seg = "sim";
                        }
                        else{
                            seg = "nao";
                        }


                    //chama EmitePassagem para gerar passagem e imprimir
                    new EmitePassagem(nova,idv.getIdSeqTdv(), idv.getData(),codigoPassagem,nova.getValorTotal(),poltronasVagas.getSelectedItem().toString(),nomePassageiro, cpfPassageiro, viagem, estud, seg, data, hora);

                        try {
                            dbm.insertPassagem(nova);
                            dbm.decrementarNumeroDeVagasDisponiveis(idv.getIdSeqTdv(), idv.getData());
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }                        

                    dispose();
                    /*
                    System.out.println(codigoPassagem);

                    int i = JOptionPane.showConfirmDialog(null, "Valor Total:\nR$ "
                            + nova.getValorTotal() + "\nCodigo Passagem: " + codigoPassagem, "Pagamento", 2);

                    if(i == 0){
                        try {
                            dbm.insertPassagem(nova);
                            dbm.decrementarNumeroDeVagasDisponiveis(idv.getIdSeqTdv(), idv.getData());
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }
                        dispose();
                        return;
                    }
                    */
                }
            }
        );
        add(concluir);

        sair.setBounds(150, 175, 100, 30);
        sair.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            }
        );
        add(sair);

        repaint();

    }

    public NovaPassagem(final InstanciaDeViagem idv, final DataBaseManager dbm,final QueryManager qm,int idCliente, final String viagem, final String data, final String hora){

        this(idv,dbm,qm,viagem,data,hora);

        this.idClienteField.setText(idCliente + "");
        this.idClienteField.setEditable(false);

    }

}
