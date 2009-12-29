package util;

import administracao.viagens.TipoDeViagem;
import cliente.Cliente;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;

public interface DataBaseManager extends Remote{

    public Connection getConnection() throws RemoteException ;
    
    public boolean closeConnection() throws RemoteException;

    public boolean insertCliente(Cliente novoCliente) throws RemoteException;

    public Cliente getCliente(int id) throws RemoteException;
    
    public boolean updateCliente(int id, Cliente mod) throws RemoteException;

    public int maximunValueCliente() throws RemoteException;

    public TipoDeViagem selectTipoDeViagem(int id_seq_tdv)  throws RemoteException;

    public void deleteTipoDeViagem(int id_seq_tdv)  throws RemoteException;

    public void updateTipoDeViagem(int id_seq_tdv, TipoDeViagem novoTdv)  throws RemoteException;

    public void insertTipoDeViagem(TipoDeViagem tdv)  throws RemoteException;

    public boolean validaEntradaAgente(String nome, String senha) throws RemoteException;

}
