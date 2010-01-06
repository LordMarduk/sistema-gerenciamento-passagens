package rodoviaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class FileManager {

    private static final String FILE_NAME = "build\\classes\\rodoviaria\\info.ser";

    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    public static void gravarId(IdRodov id){
        try {
            output = new ObjectOutputStream( new FileOutputStream( FILE_NAME ) );
            output.writeObject( id );
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static IdRodov lerId(){
        try{
            input = new ObjectInputStream( new FileInputStream( FILE_NAME ) );
            return (IdRodov) input.readObject();
        }
        catch ( FileNotFoundException ex ) {

            while(true){
                String res = JOptionPane.showInputDialog("Essa rodoviária ainda " +
                        "não foi inicializada!\nIndique o ID:");
                int id = 0;
                try{
                    id = Integer.parseInt(res);
                }
                catch(NumberFormatException ex2){
                    JOptionPane.showMessageDialog(null, "Id Invalido!", "Erro", 0);
                    continue;
                }
                IdRodov novo = new IdRodov(id);
                gravarId(novo);
                return novo;
            }

        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch ( IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
