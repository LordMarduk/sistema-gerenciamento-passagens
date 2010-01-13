package rodoviaria.passagem;

import administracao.viagens.TipoDeViagem;
import java.io.Serializable;
import util.Date;

public class Passagem implements Serializable {

	private int numPoltrona;

	private boolean seguro;
	private boolean estudante;

	private float valorTotal;

	private TipoDeViagem tipoDeViagem;

	private Date data;

	private int idCliente;

    public Passagem(int numPoltrona, boolean seguro, boolean estudante, TipoDeViagem tipoDeViagem, Date data, int idCliente) {

        this.numPoltrona = numPoltrona;

        this.seguro = seguro;
        this.estudante = estudante;

        this.tipoDeViagem = tipoDeViagem;

        this.valorTotal = tipoDeViagem.getValorEmbarque() + tipoDeViagem.getValorViagem();
        if(seguro)
            valorTotal += tipoDeViagem.getValorSeguro();
        if(estudante)
            valorTotal /= 2;

        this.data = data;
        this.idCliente = idCliente;

    }

    public TipoDeViagem getTipoDeViagem() {
        return tipoDeViagem;
    }

    public void setTipoDeViagem(TipoDeViagem TipoDeViagem) {
        this.tipoDeViagem = TipoDeViagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isEstudante() {
        return estudante;
    }

    public void setEstudante(boolean estudante) {
        this.estudante = estudante;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumPoltrona() {
        return numPoltrona;
    }

    public void setNumPoltrona(int numPoltrona) {
        this.numPoltrona = numPoltrona;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

}
