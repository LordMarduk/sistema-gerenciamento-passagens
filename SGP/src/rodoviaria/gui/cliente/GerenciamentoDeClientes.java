package rodoviaria.gui.cliente;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import rodoviaria.cliente.Cliente;
import util.DataBaseManager;
import util.JNumericField;
import util.QueryManager;
import util.TableModelII;

public class GerenciamentoDeClientes extends JFrame {

    public final DataBaseManager dbm;
    public final QueryManager qm;

    private JPanel filtersPanel = new JPanel();
    private JPanel queryResultPanel = new JPanel();

    private TableModelII tableModel;
    private JTable queryResultTable;

    private JTextField nome = new JTextField();
    private JTextField endereco = new JTextField();
    private JTextField cpf = new JNumericField(11);

    private String est[] = {"Indiferente", "Estudante", "NÃ£o-Estudante"};
    private String sex[] = {"Indiferente", "Homem", "Mulher"};

    private String query = "SELECT * FROM cliente ORDER BY id_seq_cliente";

    private JComboBox estudante = new JComboBox(est);
    private JComboBox sexo = new JComboBox(sex);

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar Novo");
    private JButton sair = new JButton("Sair");

    private JPopupMenu popupMenu;
    private JButton itens[] = {
        new JButton("Editar                        "),
        new JButton("Deletar                     "),
        new JButton("Vender Passagem")
    };

    Cliente gotten = null;

    AtualizarHandler ah = new AtualizarHandler();

    private String[] clienteColumnsNames = {"ID", "Nome", "Sexo", "Data de Nascimento",
        "CPF", "Endereço", "Telefone", "Estudante"};

    public GerenciamentoDeClientes(final DataBaseManager dbm, final QueryManager qm) {

        super("Gerenciamento de Clientes");
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setBounds(150, 10, 1000, 750);
        setLayout(null);

        this.dbm = dbm;
        this.qm = qm;

        filtersPanel.setBounds(5, 5, 985, 150);
        filtersPanel.setBorder( new EtchedBorder() );
        filtersPanel.setLayout(null);

        queryResultPanel.setBounds(5, 155, 985, 565);
        queryResultPanel.setBorder( new EtchedBorder() );
        queryResultPanel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(100, 5, 50, 20);
        filtersPanel.add(nomeLabel);
        nome.setBounds(100, 25, 300, 25);
        filtersPanel.add(nome);

        JLabel enderecoLabel = new JLabel("EndereÃ§o");
        enderecoLabel.setBounds(100, 50, 100, 20);
        filtersPanel.add(enderecoLabel);
        endereco.setBounds(100, 70, 300, 25);
        filtersPanel.add(endereco);

        JLabel cpfLabel = new JLabel("CPF");
        cpfLabel.setBounds(100, 95, 50, 20);
        filtersPanel.add(cpfLabel);
        cpf.setBounds(100, 115, 300, 25);
        filtersPanel.add(cpf);

        JLabel estudanteLabel = new JLabel("Estudante");
        estudanteLabel.setBounds(450, 20, 100, 20);
        filtersPanel.add(estudanteLabel);
        estudante.setBounds(450, 40, 200, 25);
        filtersPanel.add(estudante);

        JLabel sexoLabel = new JLabel("Sexo");
        sexoLabel.setBounds(450, 80, 100, 20);
        filtersPanel.add(sexoLabel);
        sexo.setBounds(450, 100, 200, 25);
        filtersPanel.add(sexo);

        atualizar.setBounds(700, 20, 200, 30);
        cadastrar.setBounds(700, 60, 200, 30);
        sair.setBounds(700, 100, 200, 30);

        atualizar.addActionListener( ah );

        cadastrar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    NovoCliente nc = new NovoCliente(dbm);
                    nc.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
                }
            }
        );

        sair.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            }
        );

        filtersPanel.add(atualizar);
        filtersPanel.add(cadastrar);
        filtersPanel.add(sair);

        tableModel = new TableModelII(qm, this.query, clienteColumnsNames);
        queryResultTable = new JTable(tableModel);
        JScrollPane tableScroolPane = new JScrollPane(queryResultTable);
        tableScroolPane.setBounds(5, 5, 975, 555);

        queryResultTable.addMouseListener( new TableRowsEventHandler() );
        queryResultTable.setRowSelectionAllowed(true);

        queryResultPanel.add(tableScroolPane);

        popupMenu = new JPopupMenu();
        ItensPopupMenuHandler ipmh = new ItensPopupMenuHandler();
        itens[0].addActionListener(ipmh);
        itens[1].addActionListener(ipmh);
        itens[2].addActionListener(ipmh);
        popupMenu.add(itens[0]);
        popupMenu.add(itens[1]);
        popupMenu.add(itens[2]);

        add(filtersPanel);
        add(queryResultPanel);

        filtersPanel.repaint();
        queryResultPanel.repaint();
        repaint();

    }

    public int getSelectedID(){
        return Integer.parseInt(
            queryResultTable.getValueAt(
                queryResultTable.getSelectedRow(), 0
            ).toString()
        );
    }

    public class TableRowsEventHandler implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            if( (e.getClickCount() <= 1)||(e.getButton() != e.BUTTON1) )
                return;
            int id = getSelectedID();
            try {
                gotten = dbm.getCliente(id);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            popupMenu.show(queryResultTable, e.getX(), e.getY());
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    public class ItensPopupMenuHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(itens[0])){
                EditarCliente ec = new EditarCliente(gotten, dbm);
            }
            if(e.getSource().equals(itens[1])){
                int id = getSelectedID();
                int input = JOptionPane.showConfirmDialog
                        (null, "Tem certeza que quer deletar : " + gotten.getNome() + "?", "Aviso!", 0);
                if(input == 1)
                    return;
                try {
                    dbm.deleteCliente(id);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                popupMenu.setVisible(false);
                ah.actionPerformed(new ActionEvent(atualizar, 1, ""));
            }
            if(e.getSource().equals(itens[2])){
                //vender passagem para esse cliente
            }
        }
    }

    public class AtualizarHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            boolean changed = true;
            String newQuery = "SELECT * FROM cliente";
            if(
                ( nome.getText() == null )&&( endereco.getText() == null )&&
                ( cpf.getText() == null )&&( estudante.getSelectedIndex() == 0 )&&
                ( sexo.getSelectedIndex() == 0 )
            )
                changed = false;
            if(changed){
                newQuery += " WHERE ";
                boolean already = false;
                if(nome.getText() != null){
                    already = true;
                    newQuery += "(nome LIKE '%" + nome.getText() + "%')";
                }
                if(endereco.getText() != null){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(endereco LIKE '%" + endereco.getText() + "%')";
                }
                if(cpf.getText() != null){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(cpf LIKE '%" + cpf.getText() + "%')";
                }
                if(estudante.getSelectedIndex() != 0){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(e_estudante = " + (estudante.getSelectedIndex() == 1 ? "true" : "false") + ")";
                }
                if(sexo.getSelectedIndex() != 0){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(sexo = " + (sexo.getSelectedIndex() == 1 ? "'M'" : "'F'") + ")";
                }
                query = newQuery += "ORDER BY id_seq_cliente";
                tableModel.setQuery(query);
                repaint();
            }
        }
    }

}