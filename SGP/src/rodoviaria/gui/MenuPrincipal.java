package rodoviaria.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import rodoviaria.gui.cliente.GerenciamentoDeClientes;
import util.DataBaseManager;
import util.QueryManager;

public class MenuPrincipal extends JFrame
{

    JMenu arquivo;
    JToolBar barra1, barra2, barra3, barra4, barra5, barra6, barra7;
    JSeparator espaco;
    JButton gerenciaClienteButton,vendePassagemButton;
    JMenuBar menuBar;
    JMenuItem sair;
    Icon clienteIcon,passagemIcon;
    FlowLayout layout;
    BorderLayout border;
    JLabel label, chumbo;
    JPanel painel;

    public final DataBaseManager dbm;
    public final QueryManager qm;

    public MenuPrincipal(final DataBaseManager dbm, final QueryManager qm) {
        super("Sistema de Gerenciamento de Passagens Intermunicipais/Interestaduais (Beta) - Módulo Rodoviária");

        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.dbm = dbm;
        this.qm = qm;

        layout = new FlowLayout();
        border = new BorderLayout();

        setLayout(border);

        //icones
        clienteIcon = new ImageIcon(getClass().getResource("funcionario.png"));
        passagemIcon = new ImageIcon(getClass().getResource("cliente.png"));

        //jmenus
        arquivo = new JMenu("Arquivo");

        //jmenu itens
        sair = new JMenuItem("Sair");

        arquivo.add(sair);
        //arquivo.addSeparator();

        menuBar = new JMenuBar();


        //botoes
        gerenciaClienteButton = new JButton("Gerenciar Cliente ");
        gerenciaClienteButton.setIcon(clienteIcon);

        vendePassagemButton = new JButton("Vender Passagem ");
        vendePassagemButton.setIcon(passagemIcon);

        //Fim dos Botoes

        barra1 = new JToolBar();
        barra2 = new JToolBar();

        //imagem de fundo
        label = new JLabel();
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("rodoviaria.JPG")));


        menuBar.add(arquivo);


        barra1.add(gerenciaClienteButton);

        barra2.add(vendePassagemButton);


        painel = new JPanel();
        layout.setAlignment(FlowLayout.LEFT);
        painel.setLayout(layout);

        painel.add(barra1, FlowLayout.LEFT);
        painel.add(barra2, FlowLayout.CENTER);


        setJMenuBar(menuBar);


        add(painel, BorderLayout.NORTH);


        barra1.setRollover(true);
        barra2.setRollover(true);


        add(label, BorderLayout.SOUTH);

        MenuListener list = new MenuListener();


        ButtonListener but = new ButtonListener();

        gerenciaClienteButton.addActionListener(but);
        vendePassagemButton.addActionListener(but);

        sair.addActionListener(list);


    }

    public void chamarGerenciaCliente()
    {
        GerenciamentoDeClientes gcframe = new GerenciamentoDeClientes(dbm, qm);
        gcframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void chamarVendePassagem()
    {
        /*
        GerenciaPassagem gPass = new GerenciaPassagem(dbm,qm);
        gPass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE
        *///comentado ate colocar o gerencia passagem em rmi e em ordem
    }


    private class MenuListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == sair) {
                System.exit(0);
            }

        }
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == gerenciaClienteButton) {
                chamarGerenciaCliente();
            }
            else if (event.getSource() == vendePassagemButton) {
                chamarVendePassagem();
            }
        }
    }
}

