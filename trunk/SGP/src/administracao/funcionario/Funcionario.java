package administracao.funcionario;

import java.io.Serializable;
import java.sql.Date;

public class Funcionario implements Serializable{


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
        protected Date datanascimento;

        /**
         * This attribute maps to the column cpf in the funcionario table.
         */
        protected long cpf;

        /**
         * This attribute maps to the column endereco in the funcionario table.
         */
        protected String endereco;

        /**
         * This attribute maps to the column telefone in the funcionario table.
         */
        protected long telefone;

       
        public Funcionario(){}


        /*
         * get's
         */

        public long getCpf() {
            return cpf;
        }

        public Date getDatanascimento() {
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



        /*
         * Fim do get's
         */

        /*
         * Set's
         */


        public void setCpf(long cpf) {
            this.cpf = cpf;
        }

        public void setDatanascimento(Date datanascimento) {
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

