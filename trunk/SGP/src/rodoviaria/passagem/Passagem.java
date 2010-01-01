
package rodoviaria.passagem;


import java.io.Serializable;

public class Passagem implements Serializable
{
	/** 
	 * This attribute maps to the column num_poltrona in the passagem table.
	 */
	protected int numPoltrona;

	/** 
	 * This attribute maps to the column seguro in the passagem table.
	 */
	protected short seguro;

	/** 
	 * This attribute maps to the column estudante in the passagem table.
	 */
	protected short estudante;

	/** 
	 * This attribute maps to the column valor_total in the passagem table.
	 */
	protected float valorTotal;

	/** 
	 * This attribute maps to the column id_seq_tdv in the passagem table.
	 */
	protected int idSeqTdv;

	/** 
	 * This attribute maps to the column data in the passagem table.
	 */
	protected String data;

	/** 
	 * This attribute maps to the column id_seq_agente in the passagem table.
	 */
	protected int idSeqAgente;

	/** 
	 * This attribute maps to the column id_seq_cliente in the passagem table.
	 */
	protected int idSeqCliente;

	/**
	 * Method 'Passagem'
	 * 
	 */
	public Passagem()
	{
	}

	/**
	 * Method 'getNumPoltrona'
	 * 
	 * @return int
	 */
	public int getNumPoltrona()
	{
		return numPoltrona;
	}

	/**
	 * Method 'setNumPoltrona'
	 * 
	 * @param numPoltrona
	 */
	public void setNumPoltrona(int numPoltrona)
	{
		this.numPoltrona = numPoltrona;
	}

	/**
	 * Method 'getSeguro'
	 * 
	 * @return short
	 */
	public short getSeguro()
	{
		return seguro;
	}

	/**
	 * Method 'setSeguro'
	 * 
	 * @param seguro
	 */
	public void setSeguro(short seguro)
	{
		this.seguro = seguro;
	}

	/**
	 * Method 'getEstudante'
	 * 
	 * @return short
	 */
	public short getEstudante()
	{
		return estudante;
	}

	/**
	 * Method 'setEstudante'
	 * 
	 * @param estudante
	 */
	public void setEstudante(short estudante)
	{
		this.estudante = estudante;
	}

	/**
	 * Method 'getValorTotal'
	 * 
	 * @return float
	 */
	public float getValorTotal()
	{
		return valorTotal;
	}

	/**
	 * Method 'setValorTotal'
	 * 
	 * @param valorTotal
	 */
	public void setValorTotal(float valorTotal)
	{
		this.valorTotal = valorTotal;
	}

	/**
	 * Method 'getIdSeqTdv'
	 * 
	 * @return int
	 */
	public int getIdSeqTdv()
	{
		return idSeqTdv;
	}

	/**
	 * Method 'setIdSeqTdv'
	 * 
	 * @param idSeqTdv
	 */
	public void setIdSeqTdv(int idSeqTdv)
	{
		this.idSeqTdv = idSeqTdv;
	}

	/**
	 * Method 'getData'
	 * 
	 * @return Date
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * Method 'setData'
	 * 
	 * @param data
	 */
	public void setData(String data)
	{
		this.data = data;
	}

	/**
	 * Method 'getIdSeqAgente'
	 * 
	 * @return int
	 */
	public int getIdSeqAgente()
	{
		return idSeqAgente;
	}

	/**
	 * Method 'setIdSeqAgente'
	 * 
	 * @param idSeqAgente
	 */
	public void setIdSeqAgente(int idSeqAgente)
	{
		this.idSeqAgente = idSeqAgente;
	}

	/**
	 * Method 'getIdSeqCliente'
	 * 
	 * @return int
	 */
	public int getIdSeqCliente()
	{
		return idSeqCliente;
	}

	/**
	 * Method 'setIdSeqCliente'
	 * 
	 * @param idSeqCliente
	 */
	public void setIdSeqCliente(int idSeqCliente)
	{
		this.idSeqCliente = idSeqCliente;
        }

}
