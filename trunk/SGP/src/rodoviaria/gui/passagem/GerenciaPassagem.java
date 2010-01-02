package rodoviaria.gui.passagem;

/**
 *
 * @author Jader
 */
import administracao.database.DataBaseManagerImpl;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class GerenciaPassagem extends JFrame {

    private TableModel tableModel;
    private JTable resultTable;

    public GerenciaPassagem() {

        setTitle("Gerencia Passagens");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTabbedPane abas = new JTabbedPane();

        ImageIcon passagemIcon = createImageIcon("edit_f2.png");
        ImageIcon viagemIcon = createImageIcon("viagem.png");

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        abas.add(panel1,"1 Panel");
        abas.add(panel2,"2 Panel");


        //Add the tabbed pane to this panel.
        add(abas);

        //The following line enables to use scrolling tabs.
        //tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

   
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GerenciaPassagem.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args)
    {
        GerenciaPassagem gp = new GerenciaPassagem();
        gp.setVisible(true);
    }
        
}


