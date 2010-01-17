package rodoviaria.gui.passagem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import util.JNumericField;
import util.QueryManager;
import util.TableModelII;

public class SelecionarCliente extends JFrame{

    private String query = "SELECT * FROM cliente ORDER BY id_seq_cliente";
    private String[] clienteColumnsNames = {"ID", "Nome", "Sexo", "Data de Nascimento",
        "CPF", "Endereço", "Telefone", "Estudante"};
    private final TableModelII tableModel;

    private QueryManager qm;
    private final JTable resultTable;
    private JNumericField jnf;

    public SelecionarCliente(QueryManager qm, JNumericField jnf) {

        setBounds(300,200,650,400);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        this.qm = qm;
        this.jnf = jnf;


          // cria o TableModel
          tableModel = new TableModelII(qm, this.query, clienteColumnsNames);

          resultTable = new JTable(tableModel);
          JScrollPane resultScroll = new JScrollPane(resultTable);

          resultScroll.setBounds(0,0,650,400);
          add(resultScroll);

          //evento de clicar no jtable
        resultTable.addMouseListener(new MouseClickedHandler());

        addWindowListener(
                new WindowAdapter() {
                    // desconecta-se do banco de dados e sai quando a janela for fechada

                    @Override
                    public void windowClosed(WindowEvent event) {
                        dispose();
                    }
                }
        );

    }

     private class MouseClickedHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() > 1) {
               jnf.setText(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
               dispose();
            }
        }
     }


}
