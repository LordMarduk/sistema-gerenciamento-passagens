package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JNumericField extends JTextField{

	int fieldLength;

	public JNumericField(int fl) {

        fieldLength = fl;

		this.addKeyListener(

				new KeyAdapter() {

                    @SuppressWarnings("empty-statement")
                    @Override
					public void keyTyped(KeyEvent e) {

						JNumericField field = (JNumericField) e.getSource();
						char c = e.getKeyChar();

						if (field.getText().length() < field.fieldLength) {
							if (!((Character.isDigit(c) ||
									(c == KeyEvent.VK_BACK_SPACE) ||
									(c == KeyEvent.VK_DELETE)))) {
								e.consume();
							};
						}
						else{
							if (c != KeyEvent.VK_BACK_SPACE) {
								e.consume();
							}//fim do if
						}//fim do else

					}//fim do keyTyped

				}//fim do new KeyAdapter

		);//fim do addKeyListener

	}//fim do construtor

}//fim da classe
