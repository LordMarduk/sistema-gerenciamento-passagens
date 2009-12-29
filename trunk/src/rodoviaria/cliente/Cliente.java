package rodoviaria.cliente;

import java.io.Serializable;
import util.Date;

/**
 *
 * @author Marcello Passos
 * @since 2 de dezembro de 2009
 * Modelo do Objeto Cliente
 *
 */
public class Cliente implements Serializable {

    private int codigo_cli;
    private String nome;
    private char sexo;
    private Date data_nascimento;
    private String cpf;
    private String endereco;
    private String telefone;
    private boolean eEstudante;

    private static final long serialVersionUID = -3444356005607489244L;

    public Cliente(int codigo_cli, String nome, char sexo, Date data_nascimento, String cpf, String endereco, String telefone, boolean eEstudante) {
        this.codigo_cli = codigo_cli;
        this.nome = nome;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.eEstudante = eEstudante;
    }

    public int getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(int codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public boolean isEEstudante() {
        return eEstudante;
    }

    public void setEEstudante(boolean eEstudante) {
        this.eEstudante = eEstudante;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo_cli +
                "\nNome: " + nome +
                "\nSexo: " + sexo +
                "\nEndereço: " + endereco +
                "\nTelefone" + telefone +
                "\nData de Nascimento: " + data_nascimento +
                "\nEstudante: " + eEstudante;
    }

}
