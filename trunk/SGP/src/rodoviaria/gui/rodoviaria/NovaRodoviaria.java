package rodoviaria.gui.rodoviaria;

import administracao.database.DataBaseManagerImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rodoviaria.rodoviaria.Rodoviaria;
import util.Auxiliares;
import util.JNumericField;

public class NovaRodoviaria extends JFrame{

    JTextField cidade = new JTextField();
    JTextField ddd = new JNumericField(2);
    JTextField id = new JNumericField(2);
    JTextField telefone = new JNumericField(8);
    JComboBox uf = new JComboBox(Auxiliares.UFS);
    JButton concluir = new JButton("Concluir");
    JButton sair = new JButton("Sair");

    final DataBaseManagerImpl dbm;

    public NovaRodoviaria(final DataBaseManagerImpl dbm) {

        super("Nova Rodoviária");
        setBounds(425, 250, 450, 250);
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setLayout(null);

        this.dbm = dbm;

        JLabel cl = new JLabel("Cidade");
        cl.setBounds(20, 10, 100, 20);
        add(cl);
        cidade.setBounds(20, 30, 300, 30);

        JLabel ul = new JLabel("UF");
        ul.setBounds(330, 10, 50, 20);
        add(ul);
        uf.setBounds(330, 30, 100, 30);

        JLabel dl = new JLabel("DDD");
        dl.setBounds(20, 60, 50, 20);
        add(dl);
        ddd.setBounds(20, 80, 50, 30);

        JLabel tl = new JLabel("Telefone");
        tl.setBounds(80, 60, 100, 20);
        add(tl);
        telefone.setBounds(80, 80, 150, 30);

        JLabel il = new JLabel("ID");
        il.setBounds(240, 60, 100, 20);
        add(il);
        id.setBounds(240, 80, 190, 30);
        id.setText("" + (dbm.maximunValueRodoviaria() + 1));
        id.setEditable(false);

        concluir.setBounds(20, 150, 200, 50);
        concluir.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(
                        (cidade.getText() == null)||
                        (uf.getSelectedIndex() == 0)||
                        (ddd.getText().length() != 2)||
                        (telefone.getText().length() != 8)
                    ){
                        JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
                        return;
                    }
                    int i = dbm.maximunValueRodoviaria() + 1;
                    String c = cidade.getText();
                    String u = uf.getSelectedItem().toString();
                    String t = ddd.getText() + telefone.getText();
                    Rodoviaria r = new Rodoviaria(i, c, u, t);
                    dbm.insertRodoviaria(r);
                    dispose();
                }
            }
        );

        sair.setBounds(230, 150, 200, 50);
        sair.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            }
        );

        add(cidade);
        add(uf);
        add(ddd);
        add(telefone);
        add(id);
        add(concluir);
        add(sair);

        repaint();

    }

}
