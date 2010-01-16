/*Main - Executa o programa bcWebCam, ler o codigo de barras.
 * Passa o codigo lido para validaPassagem.
 * Se ValidaPassagem retorna true, a passagem é aceita caso contario invalida
 */

package onibus;

import java.io.IOException;

public class Main {

    public static void main (String [] a){

       try
       {
          Object ob = Runtime.getRuntime().exec("bcWebCam\\bcWebCam - Portable.exe");
          ValidaPassagem vp = new ValidaPassagem(String.valueOf(ob));
       }
       catch(IOException iOException)
       {
          iOException.printStackTrace();
       }

        
        
    }
}
