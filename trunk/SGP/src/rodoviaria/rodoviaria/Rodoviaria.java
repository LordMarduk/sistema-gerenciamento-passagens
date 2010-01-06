package rodoviaria.rodoviaria;

import java.io.Serializable;

public class Rodoviaria implements Serializable {

    private int id;
    private String cidade;
    private String estado;
    private String telefone;

    public Rodoviaria(int id, String cidade, String estado, String telefone) {
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return  "ID: " + id +
                "\nCIDADE: " + cidade +
                "\nESTADO: " + estado +
                "\nTELEFONE: " + telefone;
    }

}
