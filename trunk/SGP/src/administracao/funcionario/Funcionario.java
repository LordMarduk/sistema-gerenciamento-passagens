package administracao.funcionario;

import java.io.Serializable;

public class Funcionario implements Serializable{

        protected int id_seq_funcionario;

        protected String nome;

        protected String sexo;

        protected String datanascimento;

        protected long cpf;

        protected String endereco;

        protected long telefone;

        protected String usuario;

        protected String senha;

       
        public Funcionario(){}


        /*
         * get's
         */

        public long getCpf() {
            return cpf;
        }

        public String getDatanascimento() {
            return datanascimento;
        }

        public String getEndereco() {
            return endereco;
        }

        public String getNome() {
            return nome;
        }

        public String getSexo() {
            return sexo;
        }

        public long getTelefone() {
            return telefone;
        }

        public int getIdSeqFuncionario(){
            return id_seq_funcionario;
        }

        public String getUsuario() {
            return usuario;
        }

        public String getSenha() {
            return senha;
        }

        /*
         * Fim do get's
         */

        /*
         * Set's
         */


        public void setCpf(long cpf) {
            this.cpf = cpf;
        }

        public void setDatanascimento(String datanascimento) {
            this.datanascimento = datanascimento;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public void setTelefone(long telefone) {
            this.telefone = telefone;
        }

        public void setIdSeqFuncionario (int id_seq_funcionario){
            this.id_seq_funcionario = id_seq_funcionario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
        /*
         * Fim dos set's
         */

        @Override
        public String toString() {

            StringBuffer ret = new StringBuffer();
            ret.append( "com.mycompany.myapp.dto.funcionario: " );
            ret.append("nome=" + nome );
            ret.append( ", cpf=" + cpf );
            ret.append( ", endereco=" + endereco );
            ret.append( ", telefone=" + telefone );
            return ret.toString();
        }


}

