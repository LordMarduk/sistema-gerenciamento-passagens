package rodoviaria.gui.passagem;

/**
 *
 * @author Jader
 */
import administracao.database.DataBaseManagerImpl;
import javax.swing.JTabbedPane;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import util.TableModel;
//import util.DataBaseManager;
//import util.QueryManager;
//import util.TableModelCliente;
/*
SELECT instancia_de_viagem.data,instancia_de_viagem.id_seq_tdv,instancia_de_viagem.num_vagas_disponiveis,instancia_de_viagem.observacoes,
tipo_de_viagem.valor_viagem,tipo_de_viagem.hora_prev_saida,tipo_de_viagem.hora_prev_chegada,Rodoviaria1.cidade as cidade_origem,Rodoviaria2.cidade as cidade_destino
FROM (((instancia_de_viagem INNER JOIN tipo_de_viagem ON instancia_de_viagem.id_seq_tdv = tipo_de_viagem.id_seq_tdv AND instancia_de_viagem.data >= current_date AND hora_real_saida = '')
INNER JOIN rodoviaria as Rodoviaria1 ON tipo_de_viagem.id_seq_rodov_partida = Rodoviaria1.id_seq_rodov)
INNER JOIN rodoviaria as Rodoviaria2 ON tipo_de_viagem.id_seq_rodov_chegada = Rodoviaria2.id_seq_rodov);
 * */

public class GerenciaPassagem extends JFrame {

    private TableModel tableModelViagens;
    private TableModel tableModelPassagens;
    //private TableModelCliente tmc;
    private JTable resultTableViagens;
    private JTable resultTablePassagens;
    public final DataBaseManagerImpl dbm;
    //public final QueryManager qm;
    private JTabbedPane abas;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private String comandoGenial = "SELECT instancia_de_viagem.data,instancia_de_viagem.id_seq_tdv," + "instancia_de_viagem.num_vagas_disponiveis,instancia_de_viagem.observacoes," + "tipo_de_viagem.valor_viagem,tipo_de_viagem.hora_prev_saida,tipo_de_viagem.hora_prev_chegada," + "Rodoviaria1.cidade as cidade_origem,Rodoviaria2.cidade as cidade_destino FROM (((" + "instancia_de_viagem INNER JOIN tipo_de_viagem ON instancia_de_viagem.id_seq_tdv = " + "tipo_de_viagem.id_seq_tdv AND instancia_de_viagem.data >= current_date AND hora_real_saida = '') INNER JOIN rodoviaria as Rodoviaria1 ON " + "tipo_de_viagem.id_seq_rodov_partida = Rodoviaria1.id_seq_rodov)INNER JOIN rodoviaria " + "as Rodoviaria2 ON tipo_de_viagem.id_seq_rodov_chegada = Rodoviaria2.id_seq_rodov) ORDER BY data,cidade_destino,hora_prev_saida";
    //enquanto nao tamos setando como null o que nao eh preenchido, usaremos:
    private String viagemAAcontecer = "select * from instancia_de_viagem where hora_real_saida = ''";
    private String comandoGenial2 = "select * from passagem";

    //public GerenciaPassagem(final DataBaseManager dbm, final QueryManager qm) {
    public GerenciaPassagem(final DataBaseManagerImpl dbm) {

        setTitle("Gerencia Passagens");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        this.dbm = dbm;
        //this.qm = qm;


        abas = new JTabbedPane();
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel3 = new JPanel();
        panel4 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setLayout(new BorderLayout());
        //The following line enables to use scrolling tabs.
        //abas.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        try {
            tableModelViagens = new TableModel(dbm, comandoGenial);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            tableModelPassagens = new TableModel(dbm, comandoGenial2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        // cria o delegado JTable para tableModelViagens
        resultTableViagens = new JTable(tableModelViagens);
        resultTableViagens.setAutoCreateRowSorter(true);

        resultTablePassagens = new JTable(tableModelPassagens);
        resultTablePassagens.setAutoCreateRowSorter(true);

        //panel1.add(resultTableViagens);
        panel1.add(new JScrollPane(resultTableViagens), BorderLayout.CENTER);
        abas.add(panel1, "Escolher Viagem");
        panel2.add(new JScrollPane(resultTablePassagens), BorderLayout.CENTER);
        abas.add(panel2, "Ver Passagens");
        abas.setEnabledAt(1, false);
        add(abas);

        //evento de clicar no jtable
        resultTableViagens.addMouseListener(new MouseClickedHandler());

        abas.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(abas.getSelectedIndex() == 0)
                    abas.setEnabledAt(1, false);
            }
        });

    }

    private class MouseClickedHandler extends MouseAdapter {

        //tem 2 metodos essa classe interna,e um deles chama um auxiliar mais abaixo
        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() > 1) {
                apertarNoJTable();
            }
        }

        public void apertarNoJTable() {
            int select = resultTableViagens.getSelectedRow();
            if (select >= 0) {

                try {

                    if (abas.getSelectedIndex() == 0) {

                        abas.setEnabledAt(1, true);
                        abas.setSelectedIndex(1);
                        Integer pegarIdSeqTdvNoJTable = null;
                        String pegarDataNoJTable = null;
                        //Integer pegarPoltronaNoJTable = null;

                        pegarDataNoJTable = new String(resultTableViagens.getValueAt(select, 0).toString());
                        pegarIdSeqTdvNoJTable = new Integer(resultTableViagens.getValueAt(select, 1).toString());

                        //tableModelPassagens.setQuery("SELECT * FROM passagem");
                        tableModelPassagens.setQuery("SELECT * FROM passagem  WHERE id_seq_tdv = " + pegarIdSeqTdvNoJTable + " AND data = '" + pegarDataNoJTable + "' ORDER BY num_poltrona");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

        }
    }
}


