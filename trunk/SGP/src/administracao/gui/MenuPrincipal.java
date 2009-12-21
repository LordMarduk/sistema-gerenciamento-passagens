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

    JMenu cadastros, movimentos, caixa, configuracoes, ajuda;
    JToolBar barra1, barra2, barra3, barra4, barra5, barra6, barra7;
    JSeparator espaco;
    JButton tipoDeViagemButton, viagemButton, passagemButton, locar, devolver, emAtraso, buscar;
    JMenuBar menuBar;
    JMenuItem configuracaoDeCaixa, tiposDeViagem, cadastrarviagem;
    Icon usuario, viagemIcon, passagensIcon, casa, ok, relogio, busca;
    FlowLayout layout;
    BorderLayout border;
    JLabel label, chumbo;
    JPanel painel;

    public final DataBaseManagerImpl dbm;

    public MenuPrincipal(DataBaseManagerImpl dbm) {
        super("Sistema: Venda De Passagens");

        this.dbm = dbm;

        layout = new FlowLayout();
        border = new BorderLayout();

        setLayout(border);

        //icones
        usuario = new ImageIcon(getClass().getResource("groups_f2.png"));
        viagemIcon = new ImageIcon(getClass().getResource("icone_filme.jpg"));
        casa = new ImageIcon(getClass().getResource("next_f2.png"));
        ok = new ImageIcon(getClass().getResource("back_f2.png"));
        passagensIcon = new ImageIcon(getClass().getResource("edit_f2.png"));
        relogio = new ImageIcon(getClass().getResource("day_f2.png"));
        busca = new ImageIcon(getClass().getResource("search_f2.png"));

        //jmenus
        cadastros = new JMenu("cadastros");
        movimentos = new JMenu("movimentos");
        caixa = new JMenu("caixa");
        configuracoes = new JMenu("Configuracoes");

        //jmenu itens
        configuracaoDeCaixa = new JMenuItem("Configuracoes de caixa");
        tiposDeViagem = new JMenuItem("Tipos De Viagem");
        cadastrarviagem = new JMenuItem("Cadastrar viagem");
        

        cadastros.add(tiposDeViagem);
        cadastros.addSeparator();
        cadastros.add(cadastrarviagem);

        configuracoes.addSeparator();
        configuracoes.add(configuracaoDeCaixa);


        ajuda = new JMenu("ajud");

        menuBar = new JMenuBar();


        //botoes
        tipoDeViagemButton = new JButton("Tipo De Viagem");
        tipoDeViagemButton.setIcon(usuario);


        viagemButton = new JButton("viagem");
        viagemButton.setIcon(viagemIcon);

        locar = new JButton("Locar");
        locar.setIcon(casa);

        devolver = new JButton("Devo");
        devolver.setIcon(ok);

        passagemButton = new JButton("passagem");
        passagemButton.setIcon(passagensIcon);

        emAtraso = new JButton("Atrasado");
        emAtraso.setIcon(relogio);

        buscar = new JButton("Busca");
        buscar.setIcon(busca);

        barra1 = new JToolBar();
        barra2 = new JToolBar();
        barra3 = new JToolBar();

        //imagem de fundo
        label = new JLabel();
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("spider.jpg")));

        menuBar.add(cadastros);
        menuBar.add(movimentos);
        menuBar.add(caixa);
        menuBar.add(configuracoes);
        menuBar.add(ajuda);



        barra1.add(passagemButton);
        barra1.addSeparator();
        barra1.add(viagemButton);



        barra2.add(tipoDeViagemButton);
        barra2.addSeparator();
        barra2.add(devolver);

        barra3.add(locar);
        barra3.addSeparator();
        barra3.add(emAtraso);
        barra3.addSeparator();
        barra3.add(buscar);

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

        viagemButton.addActionListener(but);

        tipoDeViagemButton.addActionListener(but);

        passagemButton.addActionListener(but);

        configuracaoDeCaixa.addActionListener(list);


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

            if (event.getSource() == configuracaoDeCaixa) {
                // criarJanelaConfiguracaoDoCaixa();
            } else if (event.getSource() == tiposDeViagem) {
                //chamarTipoDeViagem();
            } else if (event.getSource() == cadastrarviagem) {
                //criarviagemIcon();
            }

        }
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == viagemButton) {
                //criarviagemIcon();
            } else if (event.getSource() == tipoDeViagemButton) {
                chamarTipoDeViagem();
            } else if (event.getSource() == passagemButton) {
                //criarJanelaDoCaixa();
            }

        }
    }
}
