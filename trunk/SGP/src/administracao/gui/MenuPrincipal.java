package administracao.gui;

import administracao.database.DataBaseManagerImpl;
import administracao.gui.tipo_de_viagem.GerenciaTipoDeViagem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import javax.swing.JRootPane;
public class MenuPrincipal extends JFrame
{

    JMenu arquivo;
    JToolBar barra1, barra2, barra3, barra4, barra5, barra6, barra7;
    JSeparator espaco;
    JButton tipoDeViagemButton, funcionarioButton, onibusButton;
    JMenuBar menuBar;
    JMenuItem sair;
    Icon fucionarioIcon, viagemIcon, onibusIcon;
    FlowLayout layout;
    BorderLayout border;
    JLabel label, chumbo;
    JPanel painel;

    public final DataBaseManagerImpl dbm;

    public MenuPrincipal(DataBaseManagerImpl dbm) {
        super("Sistema de Gerenciamento de Passagens Intermunicipais/Interestaduais (Beta) - Módulo Administrador");

        this.dbm = dbm;

        layout = new FlowLayout();
        border = new BorderLayout();

        setLayout(border);

        //icones
        fucionarioIcon = new ImageIcon(getClass().getResource("funcionario.png"));
        viagemIcon = new ImageIcon(getClass().getResource("viagem.png"));
        onibusIcon = new ImageIcon(getClass().getResource("onibus.png"));

        //jmenus
        arquivo = new JMenu("Arquivo");

        //jmenu itens
        sair = new JMenuItem("Sair");

        arquivo.add(sair);
        //arquivo.addSeparator();

        menuBar = new JMenuBar();


        //botoes
        tipoDeViagemButton = new JButton("Administrar Viagem ");
        tipoDeViagemButton.setIcon(viagemIcon);


        funcionarioButton = new JButton("Administrar Funcionário ");
        funcionarioButton.setIcon(fucionarioIcon);

        onibusButton = new JButton("Administrar Ônibus ");
        onibusButton.setIcon(onibusIcon);
        //Fim dos Botoes

        barra1 = new JToolBar();
        barra2 = new JToolBar();
        barra3 = new JToolBar();

        //imagem de fundo
        label = new JLabel();
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("background.png")));


        menuBar.add(arquivo);


        barra1.add(funcionarioButton);

        barra2.add(tipoDeViagemButton);

        barra3.add(onibusButton);

        painel = new JPanel();
        layout.setAlignment(FlowLayout.LEFT);
        painel.setLayout(layout);

        painel.add(barra1, FlowLayout.LEFT);
        painel.add(barra2, FlowLayout.CENTER);
        painel.add(barra3, FlowLayout.RIGHT);


        setJMenuBar(menuBar);


        add(painel, BorderLayout.NORTH);


        barra1.setRollover(true);
        barra2.setRollover(true);
        barra3.setRollover(true);


        add(label, BorderLayout.SOUTH);

        MenuListener list = new MenuListener();


        ButtonListener but = new ButtonListener();

        tipoDeViagemButton.addActionListener(but);

        sair.addActionListener(list);


    }

    public void chamarTipoDeViagem()
    {
        System.out.println("tdv chamada");
    GerenciaTipoDeViagem gtdv = new GerenciaTipoDeViagem(dbm);
    gtdv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    gtdv.setLocationRelativeTo(this);
    gtdv.setSize(500, 400);
    gtdv.setVisible(true);
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

            if (event.getSource() == tipoDeViagemButton) {
                chamarTipoDeViagem();
            }
        }
    }
}
