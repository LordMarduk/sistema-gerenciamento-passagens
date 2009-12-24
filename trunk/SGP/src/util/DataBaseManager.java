package util;

import administracao.viagens.TipoDeViagem;
import administracao.viagens.InstanciaDeViagem;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataBaseManager extends Remote{
    
    public boolean closeConnection() throws RemoteException;

    public boolean insertCliente(String novoCliente) throws RemoteException;

    public int maximunValueCliente() throws RemoteException;

    public TipoDeViagem selectTipoDeViagem(int id_seq_tdv)  throws RemoteException;

    public void deleteTipoDeViagem(int id_seq_tdv)  throws RemoteException;

    public void updateTipoDeViagem(int id_seq_tdv, TipoDeViagem novoTdv)  throws RemoteException;

    public void insertTipoDeViagem(TipoDeViagem tdv)  throws RemoteException;

    public boolean validaEntradaAgente(String nome, String senha) throws RemoteException;

}
