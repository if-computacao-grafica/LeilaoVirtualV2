/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leilaocliente;

import com.mycompany.leilaoservidormulticast.compartilhado.domain.Auction;
import com.mycompany.leilaoservidormulticast.compartilhado.dtos.LanceDto;
import com.mycompany.leilaoservidormulticast.compartilhado.dtos.StreamDto;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
// import javax.xml.crypto.Data; desnecessario

/**
 *
 * @author skmat
 */
public class ClienteLicitante {    
    private String nomeLicitante;
    private MulticastSocket socket;
    private Auction auction;

    public ClienteLicitante(String nomeLicitante, Auction auction) {
        try {
            this.nomeLicitante = nomeLicitante;
            this.auction = auction;
            this.socket = new MulticastSocket(auction.getPorta());
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean joinAuction() {
        try {
            if (auction.getStatus() == Auction.INICIADO) {
                socket.joinGroup(auction.getEndereco());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return auction.getStatus() == Auction.INICIADO;
    }

    public void leaveAuction() {
        try {
            socket.leaveGroup(auction.getEndereco());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean fazerLance(int preco) {
        if (isPriceEnough(preco)) {
            try {
                ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
                ObjectOutputStream outputObject = new ObjectOutputStream(outputBytes);

                outputObject.writeObject(new StreamDto(StreamDto.REQUISICAO_LANCE, new LanceDto(preco, nomeLicitante)));
                byte[] bytesData = outputBytes.toByteArray();
                DatagramPacket packet = new DatagramPacket(bytesData, bytesData.length, auction.getEndereco(), auction.getPorta());
                socket.send(packet);
                outputObject.reset();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return isPriceEnough(preco);
    }

    public boolean isPriceEnough(int price) {
        return price > auction.getUltimoLance().getPreco();
    }

    public void listenBid(Runnable callbackBid, Runnable callbackEnd) {
        new Thread(() -> {
            while (true) {
                try {
                    byte[] incomingData = new byte[1024];
                    DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                    socket.receive(incomingPacket);

                    ByteArrayInputStream inputBytes = new ByteArrayInputStream(incomingPacket.getData());
                    ObjectInputStream inputObject = new ObjectInputStream(inputBytes);

                    StreamDto data = (StreamDto) inputObject.readObject();
                    if (data.getTipo() == StreamDto.RESPOSTA_LANCE) {
                        auction.setUltimoLance((LanceDto) data.getPayload());
                        callbackBid.run();
                    } else if (data.getTipo() == StreamDto.LEILAO_TERMINO) {
                        leaveAuction();
                        callbackEnd.run();
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
    }

    public Auction getAuction() {
        return auction;
    }
}
