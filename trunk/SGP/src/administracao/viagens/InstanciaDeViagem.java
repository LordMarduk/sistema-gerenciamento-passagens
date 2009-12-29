

package administracao.viagens;

import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class InstanciaDeViagem implements Serializable
{
	/** 
	 * This attribute maps to the column data in the instancia_de_viagem table.
	 */
	protected Date data;

	/** 
	 * This attribute maps to the column num_vagas_disponiveis in the instancia_de_viagem table.
	 */
	protected int numVagasDisponiveis;

	/** 
	 * This attribute maps to the column observacoes in the instancia_de_viagem table.
	 */
	protected String observacoes;

	/** 
	 * This attribute maps to the column id_seq_tdv in the instancia_de_viagem table.
	 */
	protected int idSeqTdv;

	/** 
	 * This attribute maps to the column hora_real_saida in the instancia_de_viagem table.
	 */
	protected String horaRealSaida;

	/** 
	 * This attribute maps to the column hora_real_chegada in the instancia_de_viagem table.
	 */
	protected String horaRealChegada;

	/** 
	 * This attribute maps to the column id_seq_carro in the instancia_de_viagem table.
	 */
	protected int idSeqCarro;

	/** 
	 * This attribute maps to the column id_seq_motorista in the instancia_de_viagem table.
	 */
	protected int idSeqMotorista;

	/**
	 * Method 'InstanciaDeViagem'
	 * 
	 */
	public InstanciaDeViagem()
	{
	}

	/**
	 * Method 'getData'
	 * 
	 * @return Date
	 */
	public Date getData()
	{
		return data;
	}

	/**
	 * Method 'setData'
	 * 
	 * @param data
	 */
	public void setData(Date data)
	{
		this.data = data;
	}

	/**
	 * Method 'getNumVagasDisponiveis'
	 * 
	 * @return int
	 */
	public int getNumVagasDisponiveis()
	{
		return numVagasDisponiveis;
	}

	/**
	 * Method 'setNumVagasDisponiveis'
	 * 
	 * @param numVagasDisponiveis
	 */
	public void setNumVagasDisponiveis(int numVagasDisponiveis)
	{
		this.numVagasDisponiveis = numVagasDisponiveis;
	}

	/**
	 * Method 'getObservacoes'
	 * 
	 * @return String
	 */
	public String getObservacoes()
	{
		return observacoes;
	}

	/**
	 * Method 'setObservacoes'
	 * 
	 * @param observacoes
	 */
	public void setObservacoes(String observacoes)
	{
		this.observacoes = observacoes;
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
	 * Method 'getHoraRealSaida'
	 * 
	 * @return String
	 */
	public String getHoraRealSaida()
	{
		return horaRealSaida;
	}

	/**
	 * Method 'setHoraRealSaida'
	 * 
	 * @param horaRealSaida
	 */
	public void setHoraRealSaida(String horaRealSaida)
	{
		this.horaRealSaida = horaRealSaida;
	}

	/**
	 * Method 'getHoraRealChegada'
	 * 
	 * @return String
	 */
	public String getHoraRealChegada()
	{
		return horaRealChegada;
	}

	/**
	 * Method 'setHoraRealChegada'
	 * 
	 * @param horaRealChegada
	 */
	public void setHoraRealChegada(String horaRealChegada)
	{
		this.horaRealChegada = horaRealChegada;
	}

	/**
	 * Method 'getIdSeqCarro'
	 * 
	 * @return int
	 */
	public int getIdSeqCarro()
	{
		return idSeqCarro;
	}

	/**
	 * Method 'setIdSeqCarro'
	 * 
	 * @param idSeqCarro
	 */
	public void setIdSeqCarro(int idSeqCarro)
	{
		this.idSeqCarro = idSeqCarro;
	}

	/**
	 * Method 'getIdSeqMotorista'
	 * 
	 * @return int
	 */
	public int getIdSeqMotorista()
	{
		return idSeqMotorista;
	}

	/**
	 * Method 'setIdSeqMotorista'
	 * 
	 * @param idSeqMotorista
	 */
	public void setIdSeqMotorista(int idSeqMotorista)
	{
		this.idSeqMotorista = idSeqMotorista;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof InstanciaDeViagem)) {
			return false;
		}
		
		final InstanciaDeViagem _cast = (InstanciaDeViagem) _other;
		if (data == null ? _cast.data != data : !data.equals( _cast.data )) {
			return false;
		}
		
		if (numVagasDisponiveis != _cast.numVagasDisponiveis) {
			return false;
		}
		
		if (observacoes == null ? _cast.observacoes != observacoes : !observacoes.equals( _cast.observacoes )) {
			return false;
		}
		
		if (idSeqTdv != _cast.idSeqTdv) {
			return false;
		}
		
		if (horaRealSaida == null ? _cast.horaRealSaida != horaRealSaida : !horaRealSaida.equals( _cast.horaRealSaida )) {
			return false;
		}
		
		if (horaRealChegada == null ? _cast.horaRealChegada != horaRealChegada : !horaRealChegada.equals( _cast.horaRealChegada )) {
			return false;
		}
		
		if (idSeqCarro != _cast.idSeqCarro) {
			return false;
		}
		
		if (idSeqMotorista != _cast.idSeqMotorista) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (data != null) {
			_hashCode = 29 * _hashCode + data.hashCode();
		}
		
		_hashCode = 29 * _hashCode + numVagasDisponiveis;
		if (observacoes != null) {
			_hashCode = 29 * _hashCode + observacoes.hashCode();
		}
		
		_hashCode = 29 * _hashCode + idSeqTdv;
		if (horaRealSaida != null) {
			_hashCode = 29 * _hashCode + horaRealSaida.hashCode();
		}
		
		if (horaRealChegada != null) {
			_hashCode = 29 * _hashCode + horaRealChegada.hashCode();
		}
		
		_hashCode = 29 * _hashCode + idSeqCarro;
		_hashCode = 29 * _hashCode + idSeqMotorista;
		return _hashCode;
	}

	

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.mycompany.myapp.dto.InstanciaDeViagem: " );
		ret.append( "data=" + data );
		ret.append( ", numVagasDisponiveis=" + numVagasDisponiveis );
		ret.append( ", observacoes=" + observacoes );
		ret.append( ", idSeqTdv=" + idSeqTdv );
		ret.append( ", horaRealSaida=" + horaRealSaida );
		ret.append( ", horaRealChegada=" + horaRealChegada );
		ret.append( ", idSeqCarro=" + idSeqCarro );
		ret.append( ", idSeqMotorista=" + idSeqMotorista );
		return ret.toString();
	}

}
