package rodoviaria.gui.passagem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import rodoviaria.passagem.Passagem;
import util.PrintUtilities;

public class EmitePassagem extends JFrame{

    private JTextArea infoPassageiro = new JTextArea(6,30);
    private JButton imprimir = new JButton("Emitir Passagem");
    private JPanel codigoBarras = new JPanel();
    private JPanel fundo = new JPanel();

    Barcode codigoDeBarras;

    EmitePassagem(Passagem nova, int idSeqTdv, String data, String codigoPassagem, float valorTotal, String acento, String nomePassageiro, String cpfPassageiro, String viagem,String estud, String seg){
        super("Passagem");

        setBounds(500, 300, 355, 260);
        setResizable(false);
        setVisible(true);
        setLayout(null);


        fundo.setBounds(0, 0, 350, 180);
        fundo.setBackground(Color.white);
        add(fundo);

        infoPassageiro.setText("   Nome: "+nomePassageiro+
                               "\n   Estudante: "+estud+" Seguro: "+seg+
                               "\n   CPF: "+cpfPassageiro+
                               "\n   Viagem: "+viagem+
                               "\n   Poltrona: "+acento+
                               "\n   Valor Total: R$" + valorTotal);
        infoPassageiro.setBounds(0,0,350,100);
        infoPassageiro.setBorder(null);
        infoPassageiro.setEditable(false);
        fundo.add(infoPassageiro);
        
        try {
            codigoDeBarras = BarcodeFactory.createCodabar(codigoPassagem);
        } catch (BarcodeException ex) {
            ex.printStackTrace();
        }

        codigoBarras.add(codigoDeBarras);
        codigoBarras.setBounds(45, 110, 320, 80);
        codigoBarras.setBackground(Color.white);
        fundo.add(codigoBarras);
       
        imprimir.setBounds(100, 190, 150, 40);
        imprimir.addActionListener(

            new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                        PrintUtilities.printComponent(fundo);
                        dispose();

                        /*//salavar em arquivo
                        int w = fundo.getWidth();
                        int h = fundo.getHeight();
                        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                        Graphics2D g = bi.createGraphics();
                        fundo.paint(g);

                        try {
                            ImageIO.write(bi, "PNG", new File("passagem.png"));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        */
                        
                }

            }

        );
        add(imprimir);

        /*
        janela.setBounds(0, 0, 360, 240);
        janela.add(infoPassageiro);
        janela.add(codigoBarras);
        janela.add(imprimir);
        add(janela);
        */

        repaint();
    }

}
