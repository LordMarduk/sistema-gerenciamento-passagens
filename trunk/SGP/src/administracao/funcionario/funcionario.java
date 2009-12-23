package administracao.funcionario;

import java.io.Serializable;

public class funcionario implements Serializable{

        /**
	 * This attribute maps to the column id_seq_funcionario in the funcionario table.
	 */
	protected int id_seq_funcionario;

        /**
	 * This attribute maps to the column nome in the funcionario table.
	 */
	protected String nome;

        /**
	 * This attribute maps to the column sexo in the funcionario table.
	 */
	protected String sexo;

        /**
	 * This attribute maps to the column data in the funcionario table.
	 */
	protected String datanascimento;

        /**
	 * This attribute maps to the column cpf in the funcionario table.
	 */
	protected int cpf;

        /**
	 * This attribute maps to the column endereco in the funcionario table.
	 */
	protected String endereco;

        /**
	 * This attribute maps to the column telefone in the funcionario table.
	 */
	protected int telefone;

        /**
	 * This attribute maps to the column usuario in the agente table.
	 */
	protected String usuario;

        /**
	 * This attribute maps to the column senha in the agente table.
	 */
	protected String senha;

        /**
	 * This attribute maps to the column cnh in the motorista table.
	 */
	protected String cnh;



        /*
         * get's
         */

        public String getCnh() {
            return cnh;
        }

        public int getCpf() {
            return cpf;
        }

        public String getDatanascimento() {
            return datanascimento;
        }

        public String getEndereco() {
            return endereco;
        }

        public int getId_seq_funcionario() {
            return id_seq_funcionario;
        }

        public String getNome() {
            return nome;
        }

        public String getSenha() {
            return senha;
        }

        public String getSexo() {
            return sexo;
        }

        public int getTelefone() {
            return telefone;
        }

        public String getUsuario() {
            return usuario;
        }



        /*
         * Fim do get's
         */

        /*
         * Set's
         */

        public void setCnh(String cnh) {
            this.cnh = cnh;
        }

        public void setCpf(int cpf) {
            this.cpf = cpf;
        }

        public void setDatanascimento(String datanascimento) {
            this.datanascimento = datanascimento;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public void setId_seq_funcionario(int id_seq_funcionario) {
            this.id_seq_funcionario = id_seq_funcionario;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public void setTelefone(int telefone) {
            this.telefone = telefone;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }


        /*
         * Fim dos set's
         */

        @Override
        public String toString() {

            StringBuffer ret = new StringBuffer();
            ret.append( "com.mycompany.myapp.dto.funcionario: " );
            ret.append("id_seq_funcionario=" + id_seq_funcionario);
            ret.append( ", nome=" + nome );
            ret.append( ", sexo=" + sexo );
            ret.append( ", datanascimento=" + datanascimento );
            ret.append( ", cpf=" + cpf );
            ret.append( ", endereco=" + endereco );
            ret.append( ", telefone=" + telefone );
            ret.append( ", usuario=" + usuario );
            ret.append( ", senha=" + senha );
            ret.append( ", cnh=" + cnh );
            return ret.toString();
        }

}
