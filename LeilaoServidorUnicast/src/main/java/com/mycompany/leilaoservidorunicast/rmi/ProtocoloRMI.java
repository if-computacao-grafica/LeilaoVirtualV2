package com.mycompany.leilaoservidorunicast.rmi;

import com.mycompany.leilaoservidormulticast.compartilhado.domain.Auction;
import com.mycompany.leilaoservidorunicast.comunicacaoexterna.ClienteLicitanteConcorrente;
import com.mycompany.leilaoservidorunicast.domain.Cliente;
import com.mycompany.leilaoservidorunicast.util.CriptografiaUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProtocoloRMI implements IProtocoloRMI {
    private HashMap<Integer, Cliente> clientes = new HashMap<>();
    private PublicKey chavePublica;
    private ClienteLicitanteConcorrente bidderCandidate = new ClienteLicitanteConcorrente();
    private byte[]  obj = null;
    
    public HashMap<Integer, Cliente> getClientes() {
        return clientes;
    }

    public Cliente getCliente(int id) {
        return clientes.get(id);
    }
        
    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    public byte[] obterMensagemCifrada(byte[] bytesGerados) {
        return CriptografiaUtils.cifrarTexto(chavePublica, bytesGerados);
    }

    public byte[] solicitarChaveAES(int clientID){
        byte[] obj = null;
        try {
            Cliente clienteSolicitante = clientes.get(clientID);
            Key chaveAES = CriptografiaUtils.getChave("C:\\chaves\\chaveAES\\chaveSimetricaServidor");
            
            obj = CriptografiaUtils.cifrarTexto(
                                    clienteSolicitante.getChavePublica(),
                                    chaveAES.getEncoded());
 
        } catch(Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    // Metodos para envio de endereços de multicast
    public byte[] solicitarEnderecosMulticast(int idCliente) throws RemoteException{
        try {
            Cliente clienteSolicitante = clientes.get(idCliente);
            bidderCandidate.enviarListaRequisicoesDoLeilao();
            byte[] byteArray;
            
            ArrayList<Auction> array = bidderCandidate.listenListaLeilao();
            ArrayList<String> enderecos = new ArrayList<String>();
            for (Auction auction : array) {
                if(auction.getStatus() == 2){
                    enderecos.add(
                                auction.getEndereco().getHostAddress()
                                + " " +
                                auction.getProduto().getNome());
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ObjectOutputStream oos;
            oos = new ObjectOutputStream(baos);
            oos.writeObject(enderecos);

            // Convert to Byte Array
            byteArray = baos.toByteArray();
            obj = CriptografiaUtils.cifrarTexto(
                                    clienteSolicitante.getChavePublica(),
                                    byteArray);

            return obj;
        } catch (IOException ex) {
            Logger.getLogger(ProtocoloRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProtocoloRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}