package com.mycompany.leilaoservidorunicast.rmi;

import com.mycompany.leilaoservidormulticast.compartilhado.domain.Auction;
import com.mycompany.leilaoservidorunicast.domain.Cliente;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
public interface IProtocoloRMI extends Remote {
    public HashMap<Integer, Cliente> getClientes() throws RemoteException;
    public Cliente getCliente(int id) throws RemoteException;
    
    // Criações
    public void addCliente(Cliente clientes) throws RemoteException; 
    
    // Criptografia
    public byte[] obterMensagemCifrada(byte[] mensagem) throws RemoteException;    
    public byte[] solicitarEnderecosMulticast(int clientID) throws RemoteException;
    public byte[] solicitarChaveAES(int idCliente) throws RemoteException; 
    
}