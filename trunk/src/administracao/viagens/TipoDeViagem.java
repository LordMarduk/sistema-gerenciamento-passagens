package administracao.viagens;

import java.io.Serializable;

/**
 *
 * @author Jader James
 * Objetos tipo de viagem
 *
 */

public class TipoDeViagem implements Serializable
{
	/**
	 * This attribute maps to the column id_seq_tdv in the tipo_de_viagem table.
	 */
	protected int idSeqTdv;

	/**
	 * This attribute maps to the column valor_viagem in the tipo_de_viagem table.
	 */
	protected float valorViagem;

	/**
	 * This attribute maps to the column valor_seguro in the tipo_de_viagem table.
	 */
	protected float valorSeguro;

	/**
	 * This attribute maps to the column valor_embarque in the tipo_de_viagem table.
	 */
	protected float valorEmbarque;

	/**
	 * This attribute maps to the column dias_da_semana in the tipo_de_viagem table.
	 */
	protected String diasDaSemana;

	/**
	 * This attribute maps to the column hora_prev_saida in the tipo_de_viagem table.
	 */
	protected String horaPrevSaida;

	/**
	 * This attribute maps to the column hora_prev_chegada in the tipo_de_viagem table.
	 */
	protected String horaPrevChegada;

	/**
	 * This attribute maps to the column id_seq_rodov_partida in the tipo_de_viagem table.
	 */
	protected int idSeqRodovPartida;

	/**
	 * This attribute maps to the column id_seq_rodov_chegada in the tipo_de_viagem table.
	 */
	protected int idSeqRodovChegada;

	/**
	 * Method 'TipoDeViagem'
	 *
	 */
	public TipoDeViagem()
	{
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
	 * Method 'getValorViagem'
	 *
	 * @return float
	 */
	public float getValorViagem()
	{
		return valorViagem;
	}

	/**
	 * Method 'setValorViagem'
	 *
	 * @param valorViagem
	 */
	public void setValorViagem(float valorViagem)
	{
		this.valorViagem = valorViagem;
	}

	/**
	 * Method 'getValorSeguro'
	 *
	 * @return float
	 */
	public float getValorSeguro()
	{
		return valorSeguro;
	}

	/**
	 * Method 'setValorSeguro'
	 *
	 * @param valorSeguro
	 */
	public void setValorSeguro(float valorSeguro)
	{
		this.valorSeguro = valorSeguro;
	}

	/**
	 * Method 'getValorEmbarque'
	 *
	 * @return float
	 */
	public float getValorEmbarque()
	{
		return valorEmbarque;
	}

	/**
	 * Method 'setValorEmbarque'
	 *
	 * @param valorEmbarque
	 */
	public void setValorEmbarque(float valorEmbarque)
	{
		this.valorEmbarque = valorEmbarque;
	}

	/**
	 * Method 'getDiasDaSemana'
	 *
	 * @return String
	 */
	public String getDiasDaSemana()
	{
		return diasDaSemana;
	}

	/**
	 * Method 'setDiasDaSemana'
	 *
	 * @param diasDaSemana
	 */
	public void setDiasDaSemana(String diasDaSemana)
	{
		this.diasDaSemana = diasDaSemana;
	}

	/**
	 * Method 'getHoraPrevSaida'
	 *
	 * @return String
	 */
	public String getHoraPrevSaida()
	{
		return horaPrevSaida;
	}

	/**
	 * Method 'setHoraPrevSaida'
	 *
	 * @param horaPrevSaida
	 */
	public void setHoraPrevSaida(String horaPrevSaida)
	{
		this.horaPrevSaida = horaPrevSaida;
	}

	/**
	 * Method 'getHoraPrevChegada'
	 *
	 * @return String
	 */
	public String getHoraPrevChegada()
	{
		return horaPrevChegada;
	}

	/**
	 * Method 'setHoraPrevChegada'
	 *
	 * @param horaPrevChegada
	 */
	public void setHoraPrevChegada(String horaPrevChegada)
	{
		this.horaPrevChegada = horaPrevChegada;
	}

	/**
	 * Method 'getIdSeqRodovPartida'
	 *
	 * @return int
	 */
	public int getIdSeqRodovPartida()
	{
		return idSeqRodovPartida;
	}

	/**
	 * Method 'setIdSeqRodovPartida'
	 *
	 * @param idSeqRodovPartida
	 */
	public void setIdSeqRodovPartida(int idSeqRodovPartida)
	{
		this.idSeqRodovPartida = idSeqRodovPartida;
	}

	/**
	 * Method 'getIdSeqRodovChegada'
	 *
	 * @return int
	 */
	public int getIdSeqRodovChegada()
	{
		return idSeqRodovChegada;
	}

	/**
	 * Method 'setIdSeqRodovChegada'
	 *
	 * @param idSeqRodovChegada
	 */
	public void setIdSeqRodovChegada(int idSeqRodovChegada)
	{
		this.idSeqRodovChegada = idSeqRodovChegada;
	}


	/**
	 * Method 'toString'
	 *
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.mycompany.myapp.dto.TipoDeViagem: " );
		ret.append( "idSeqTdv=" + idSeqTdv );
		ret.append( ", valorViagem=" + valorViagem );
		ret.append( ", valorSeguro=" + valorSeguro );
		ret.append( ", valorEmbarque=" + valorEmbarque );
		ret.append( ", diasDaSemana=" + diasDaSemana );
		ret.append( ", horaPrevSaida=" + horaPrevSaida );
		ret.append( ", horaPrevChegada=" + horaPrevChegada );
		ret.append( ", idSeqRodovPartida=" + idSeqRodovPartida );
		ret.append( ", idSeqRodovChegada=" + idSeqRodovChegada );
		return ret.toString();
	}

}
