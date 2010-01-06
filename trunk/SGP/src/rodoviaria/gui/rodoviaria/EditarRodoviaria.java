package rodoviaria.gui.rodoviaria;

import administracao.database.DataBaseManagerImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rodoviaria.rodoviaria.Rodoviaria;
import util.Auxiliares;
import util.JNumericField;

public class EditarRodoviaria extends JFrame{

    JTextField cidade = new JTextField();
    JTextField ddd = new JNumericField(2);
    JTextField id = new JNumericField(2);
    JTextField telefone = new JNumericField(8);
    JComboBox uf = new JComboBox(Auxiliares.UFS);
    JButton concluir = new JButton("Concluir");
    JButton sair = new JButton("Sair");

    final DataBaseManagerImpl dbm;
    private Rodoviaria rod;

    public EditarRodoviaria(final DataBaseManagerImpl dbm, int id_rod) throws RemoteException {
        this(dbm, dbm.getRodoviaria(id_rod));
    }

    public EditarRodoviaria(final DataBaseManagerImpl dbm, final Rodoviaria rod) {

        super("Editar: " + rod.getId());
        setBounds(425, 250, 450, 250);
        setDefaultCloseOperation(2); //dispose on close
        setVisible(true);
        setResizable(false);
        setLayout(null);

        this.dbm = dbm;
        this.rod = rod;

        JLabel cl = new JLabel("Cidade");
        cl.setBounds(20, 10, 100, 20);
        add(cl);
        cidade.setBounds(20, 30, 300, 30);
        cidade.setText(rod.getCidade());

        JLabel ul = new JLabel("UF");
        ul.setBounds(330, 10, 50, 20);
        add(ul);
        uf.setBounds(330, 30, 100, 30);
        uf.setSelectedItem(rod.getEstado());

        JLabel dl = new JLabel("DDD");
        dl.setBounds(20, 60, 50, 20);
        add(dl);
        ddd.setBounds(20, 80, 50, 30);
        ddd.setText(rod.getTelefone().substring(0, 2));

        JLabel tl = new JLabel("Telefone");
        tl.setBounds(80, 60, 100, 20);
        add(tl);
        telefone.setBounds(80, 80, 150, 30);
        telefone.setText(rod.getTelefone().substring(2));

        JLabel il = new JLabel("ID");
        il.setBounds(240, 60, 100, 20);
        add(il);
        id.setBounds(240, 80, 190, 30);
        id.setText("" + rod.getId());
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
                    rod.setCidade( cidade.getText() );
                    rod.setEstado( uf.getSelectedItem().toString() );
                    rod.setTelefone( ddd.getText() + telefone.getText() );
                    dbm.updateRodoviaria(rod.getId(), rod);
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
