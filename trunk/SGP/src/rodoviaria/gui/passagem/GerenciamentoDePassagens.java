package rodoviaria.gui.passagem;

import administracao.viagens.InstanciaDeViagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import util.DataBaseManager;
import util.QueryManager;
import util.TableModelII;

public class GerenciamentoDePassagens extends JFrame{

    private String[] columns = {"Orígem", "Destino", "Data", "Tipo de Viagem",
        "Vagas", "Valor", "Saída Prevista", "Chegada Prevista"};

    private String query =
            "SELECT " +
                "Rodoviaria1.cidade as cidade_origem, " +
                "Rodoviaria2.cidade as cidade_destino, " +
                "instancia_de_viagem.data, " +
                "instancia_de_viagem.id_seq_tdv, " +
                "instancia_de_viagem.num_vagas_disponiveis, " +
                "tipo_de_viagem.valor_viagem, " +
                "tipo_de_viagem.hora_prev_saida, " +
                "tipo_de_viagem.hora_prev_chegada " +
            "FROM ( ( (" +
                "instancia_de_viagem " +
                "INNER JOIN tipo_de_viagem ON instancia_de_viagem.id_seq_tdv = tipo_de_viagem.id_seq_tdv AND instancia_de_viagem.data >= current_date AND hora_real_saida = '') " +
                "INNER JOIN rodoviaria as Rodoviaria1 ON tipo_de_viagem.id_seq_rodov_partida = Rodoviaria1.id_seq_rodov) " +
                "INNER JOIN rodoviaria as Rodoviaria2 ON tipo_de_viagem.id_seq_rodov_chegada = Rodoviaria2.id_seq_rodov) " +
            "ORDER BY data, cidade_destino, hora_prev_saida";

    private final QueryManager qm;
    private final DataBaseManager dbm;

    private JPopupMenu popupMenu;
    private JButton[] itens = { new JButton("Ver Passagens      "), new JButton("Vender Passagem") };

    private InstanciaDeViagem idv;

    private TableModelII tableModel;
    private JTable resultTable;

    private int idCliente = 0;

    public GerenciamentoDePassagens(final QueryManager qm, final DataBaseManager dbm){

        super("Viagens Disponiveis");
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setBounds(150, 10, 1000, 750);
        setLayout(null);

        this.dbm = dbm;
        this.qm = qm;

        JLabel label = new JLabel("Escolha a Viagem: ");
        label.setBounds(25, 50, 150, 30);
        add(label);

        tableModel = new TableModelII(qm, query, columns);
        resultTable = new JTable(tableModel);
        resultTable.addMouseListener( new TableRowsEventHandler() );
        JScrollPane tableScroll = new JScrollPane(resultTable);
        tableScroll.setBounds(20, 100, 960, 610);

        popupMenu = new JPopupMenu();
        ItensPopupMenuHandler ipmh = new ItensPopupMenuHandler();
        itens[0].addActionListener(ipmh);
        itens[1].addActionListener(ipmh);
        popupMenu.add( itens[0] );
        popupMenu.add( itens[1] );

        add(tableScroll);

        repaint();

    }

    public GerenciamentoDePassagens(final QueryManager qm, final DataBaseManager dbm, int idCliente){

        this(qm, dbm);
        this.idCliente = idCliente;

    }
    
    public int getSelectedID(){
        return Integer.parseInt(
            resultTable.getValueAt(
                resultTable.getSelectedRow(), 3
            ).toString()
        );
    }

    public String getSelectedData(){
        return resultTable.getValueAt( resultTable.getSelectedRow(), 2 ).toString();
    }

    public class ItensPopupMenuHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(itens[0])){
                System.out.println("opcao 1");
            }
            if(e.getSource().equals(itens[1])){
                NovaPassagem np = null;
                if(idCliente != 0)
                    np = new NovaPassagem(idv, dbm, idCliente);
                else
                    np = new NovaPassagem(idv, dbm);
                dispose();
            }
        }
    }

    public class TableRowsEventHandler implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            if( (e.getClickCount() <= 1)||(e.getButton() != MouseEvent.BUTTON1) )
                return;
            int id = getSelectedID();
            String data = getSelectedData();
            try {
                idv = dbm.selectInstanciaDeViagem(id, data);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            popupMenu.show(resultTable, e.getX(), e.getY());
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

}
