/*||||||||||||||||||||||||||||||||||||||||||||||
||                                            ||
|| Descricao da variavel inteira operacao     ||
||                                            ||
|| 0 - Tabela carro                           ||
|| 1 - Tabela motorista                       ||
|| 2 - Tabela rodoviaria  (partida)           ||
|| 3 - Tabela rodoviaria  (chegada)           ||
|| 4 - Tabela Tipo de Viagem                  ||
||                                            ||
||||||||||||||||||||||||||||||||||||||||||||||*/

package administracao.gui.viagens;

import administracao.carro.Carro;
import administracao.database.DataBaseManagerImpl;
import administracao.funcionario.Funcionario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import administracao.rodoviaria.Rodoviaria;
import administracao.viagens.TipoDeViagem;
import util.JNumericField;
import util.TableModel;

public class SelecionarIds extends JFrame {

    private JTable resultTable;
    private TableModel tableModel;

    public final DataBaseManagerImpl dbm;

    private JNumericField jnf,jnf2;

    int operacao;

    public SelecionarIds (final DataBaseManagerImpl dbm, int operacao, JNumericField jnf, JNumericField jnf2){

        setBounds(300,200,650,400);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        //-----

        this.dbm = dbm;
        this.jnf = jnf;
        this.jnf2 = jnf2;

        //-----

        if(operacao == 0) {
            try {
                // cria o TableModel
                tableModel = new TableModel(dbm, "SELECT * FROM carro ORDER BY placa");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(operacao == 1){
            try {
                // cria o TableModel
                tableModel = new TableModel(dbm, "SELECT * FROM funcionario INNER JOIN motorista "+
                                                 "ON funcionario.id_seq_funcionario=motorista.id_seq_motorista "+
                                                 "ORDER BY nome");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(operacao == 2 || operacao == 3){
            try {
                // cria o TableModel
                tableModel = new TableModel(dbm, "SELECT * FROM rodoviaria ORDER BY cidade");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        else if(operacao == 4){
            try {
                // cria o TableModel
                tableModel = new TableModel(dbm, "SELECT * FROM tipo_de_viagem ORDER BY id_seq_tdv");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        resultTable = new JTable(tableModel);
        JScrollPane resultScroll = new JScrollPane(resultTable);

        resultScroll.setBounds(0,0,650,400);
        add(resultScroll);

        //evento de clicar no jtable
        resultTable.addMouseListener(new MouseClickedHandler(operacao));

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

        private final int operacao;
        
        public MouseClickedHandler (int operacao){
            this.operacao = operacao;
        }

        //tem 2 metodos essa classe interna,e um deles chama um auxiliar mais abaixo
        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() > 1) {
                apertarNoJTable();
            }
        }

        public void apertarNoJTable() {
            int select = resultTable.getSelectedRow();
            if (select >= 0) {

                try {
                     //lugar especifico onde clicou
                    Integer clicado = new Integer(resultTable.getValueAt(select, 0).toString());

                    if(this.operacao == 0) {
                        //quando buscar esse sera preenchido
                        Carro car = new Carro();
                        //efetua a busca e preenche
                        car = dbm.selectCarro(clicado);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        preencherIdCarroEmInstDeViagem(car);

                        dispose();
                    }
                    if(this.operacao == 1){
                        //quando buscar esse sera preenchido
                        Funcionario fun = new Funcionario();
                        //efetua a busca e preenche
                        fun = dbm.selectMotorista(clicado);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        preencherIdMotoristaEmInstDeViagem(fun);

                        dispose();
                    }
                    if(this.operacao == 2 || this.operacao == 3){
                        //quando buscar esse sera preenchido
                        Rodoviaria rod = new Rodoviaria(0,null,null,null);
                        //efetua a busca e preenche
                        rod = dbm.getRodoviaria(clicado);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        preencherIdRodoviariaEmInstDeViagem(rod);

                        dispose();
                    }

                    if(this.operacao == 4) {
                        //quando buscar esse sera preenchido
                        TipoDeViagem tdv = new TipoDeViagem();
                        //efetua a busca e preenche
                        tdv = dbm.selectTipoDeViagem(clicado);
                        //janela que exibira os dados    mapeamento: objeto -> GUI
                        preencherIdTipoDeViagem(tdv);

                        dispose();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

        }

        public void preencherIdCarroEmInstDeViagem(Carro car) throws ParseException {
           
               jnf.setText(String.valueOf(car.getId_seq_carro()));
               jnf2.setText(String.valueOf(car.getCapacidade()));
               dispose();
        }

        public void preencherIdMotoristaEmInstDeViagem(Funcionario fun) throws ParseException {


                jnf.setText(String.valueOf(fun.getIdSeqFuncionario()));
                //jnf.setEnabled(true);
                dispose();

        }

        public void preencherIdRodoviariaEmInstDeViagem(Rodoviaria rod) throws ParseException {


                if(operacao == 2){
                    jnf.setText(String.valueOf(rod.getId()));
                    //ctv.setEnabled(true);
                    dispose();
                }
                else if(operacao == 3){
                    jnf.setText(String.valueOf(rod.getId()));
                    //ctv.setEnabled(true);
                    dispose();
                }

            

        }

        public void preencherIdTipoDeViagem (TipoDeViagem tdv) throws ParseException {

            jnf.setText(String.valueOf(tdv.getIdSeqTdv()));
            dispose();

        }

    }
}
