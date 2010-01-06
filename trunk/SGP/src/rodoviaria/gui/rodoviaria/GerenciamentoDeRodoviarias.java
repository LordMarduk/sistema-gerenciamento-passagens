package rodoviaria.gui.rodoviaria;

import administracao.database.DataBaseManagerImpl;
import administracao.database.QueryManagerImpl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import rodoviaria.rodoviaria.Rodoviaria;
import util.Auxiliares;
import util.TableModelII;

public class GerenciamentoDeRodoviarias extends JFrame{
    
    final DataBaseManagerImpl dbm;
    final QueryManagerImpl qm;

    private TableModelII tableModel;
    private JTable resultTable;
    
    private String query = "SELECT * FROM rodoviaria ORDER BY id_seq_rodov";
    
    private JTextField cidade = new JTextField();
    private JComboBox uf = new JComboBox(Auxiliares.UFS);

    private JButton atualizar = new JButton("Atualizar");
    private JButton cadastrar = new JButton("Cadastrar Nova");
    private JButton sair = new JButton("Sair");

    private String[] rodoColumnsNames = {"ID", "Cidade", "Estado", "Telefone"};

    private JPopupMenu popupMenu;
    private JButton[] itens = { new JButton("Editar  "), new JButton("Deletar") };

    Rodoviaria rod;

    private AtualizarHandler ah = new AtualizarHandler();
            
    public GerenciamentoDeRodoviarias(final DataBaseManagerImpl dbm, final QueryManagerImpl qm) {

        super("Gerenciamento de Rodoviarias");
        setBounds(390,220,500,400);
        setDefaultCloseOperation(2); //dispose on close
        setResizable(false);
        setVisible(true);
        setLayout(null);

        this.dbm = dbm;
        this.qm = qm;

        JLabel rl = new JLabel("    Rodoviarias");
        rl.setBounds(20, 50, 80, 25);
        rl.setBorder(
            BorderFactory.createLineBorder(Color.BLACK)
        );
        add(rl);

        JLabel cl = new JLabel("Cidade");
        cl.setBounds(120, 10, 100, 20);
        add(cl);
        cidade.setBounds(120, 30, 200, 25);
        add(cidade);

        JLabel ul = new JLabel("UF");
        ul.setBounds(120, 55, 50, 20);
        add(ul);
        uf.setBounds(120, 75, 200, 25);
        add(uf);

        atualizar.setBounds(350, 20, 125, 25);
        cadastrar.setBounds(350, 50, 125, 25);
        sair.setBounds(350, 80, 125, 25);

        add(atualizar);
        add(cadastrar);
        add(sair);

        tableModel = new TableModelII(qm, query, rodoColumnsNames);
        resultTable = new JTable(tableModel);
        resultTable.addMouseListener( new TableRowsEventHandler() );
        JScrollPane tableScroll = new JScrollPane(resultTable);
        tableScroll.setBounds(20, 125, 460, 225);

        popupMenu = new JPopupMenu();
        ItensPopupMenuHandler ipmh = new ItensPopupMenuHandler();
        itens[0].addActionListener(ipmh);
        itens[1].addActionListener(ipmh);
        popupMenu.add(itens[0]);
        popupMenu.add(itens[1]);

        atualizar.addActionListener( ah );

        sair.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            }
        );

        cadastrar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    NovaRodoviaria nr = new NovaRodoviaria(dbm);
                }
            }
        );

        add(tableScroll);

        repaint();

    }
    
    public int getSelectedID(){
        return Integer.parseInt(
            resultTable.getValueAt(
                resultTable.getSelectedRow(), 0
            ).toString()
        );
    }

    public class ItensPopupMenuHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(itens[0])){
                EditarRodoviaria er = new EditarRodoviaria(dbm, rod);
            }
            if(e.getSource().equals(itens[1])){
                int id = getSelectedID();
                int input = JOptionPane.showConfirmDialog
                        (null, "Tem certeza que quer deletar : " + rod.getId() + "?", "Aviso!", 0);
                if(input == 1)
                    return;
                dbm.deleteRodoviaria(id);
                popupMenu.setVisible(false);
                ah.actionPerformed(new ActionEvent(atualizar, 1, ""));
            }
        }
    }

    public class TableRowsEventHandler implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            if( (e.getClickCount() <= 1)||(e.getButton() != e.BUTTON1) )
                return;
            int id = getSelectedID();
            try {
                rod = dbm.getRodoviaria(id);
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

    public class AtualizarHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            boolean changed = true;
            String newQuery = "SELECT * FROM rodoviaria";
            if( ( cidade.getText() == null )&&( uf.getSelectedIndex() == 0 ) )
                changed = false;
            if(changed){
                newQuery += " WHERE ";
                boolean already = false;
                if(cidade.getText() != null){
                    already = true;
                    newQuery += "(cidade LIKE '%" + cidade.getText() + "%')";
                }
                if(uf.getSelectedIndex() != 0){
                    if(already) newQuery += " AND ";
                    already = true;
                    newQuery += "(estado LIKE '%" + uf.getSelectedItem() + "%')";
                }
                query = newQuery += "ORDER BY id_seq_rodov";
                tableModel.setQuery(query);
                repaint();
            }
        }
    }

}
