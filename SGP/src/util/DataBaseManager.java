package util;

import administracao.rodoviaria.Rodoviaria;
import administracao.viagens.TipoDeViagem;
import administracao.viagens.InstanciaDeViagem;
import rodoviaria.cliente.Cliente;
import java.rmi.Remote;
import java.rmi.RemoteException;
import rodoviaria.passagem.Passagem;


public interface DataBaseManager extends Remote{

    public boolean closeConnection() throws RemoteException;

    public boolean insertCliente(Cliente novoCliente) throws RemoteException;

    public Cliente getCliente(int id) throws RemoteException;

    public boolean updateCliente(int id, Cliente mod) throws RemoteException;

    public boolean deleteCliente(int id) throws RemoteException;

    public int maximunValueCliente() throws RemoteException;

    public Rodoviaria getRodoviaria(int id) throws RemoteException;

    public TipoDeViagem selectTipoDeViagem(int id_seq_tdv)  throws RemoteException;

    public void deleteTipoDeViagem(int id_seq_tdv)  throws RemoteException;

    public void updateTipoDeViagem(int id_seq_tdv, TipoDeViagem novoTdv)  throws RemoteException;

    public void insertTipoDeViagem(TipoDeViagem tdv)  throws RemoteException;

    public boolean validaEntradaAgente(String nome, String senha) throws RemoteException;

    public boolean insertPassagem(Passagem novaPassagem) throws RemoteException;

    public Passagem getPassagem(int idTdv, Date data, int np) throws RemoteException;

    public int[] getPoltronasVagas(InstanciaDeViagem idv) throws RemoteException;

    public boolean deletePassagem(int idTdv, Date data, int np) throws RemoteException;

    public InstanciaDeViagem selectInstanciaDeViagem(int id_seq_tdv, String data) throws RemoteException;

    public boolean decrementarNumeroDeVagasDisponiveis(int id_seq_tdv, String data) throws RemoteException;

}
