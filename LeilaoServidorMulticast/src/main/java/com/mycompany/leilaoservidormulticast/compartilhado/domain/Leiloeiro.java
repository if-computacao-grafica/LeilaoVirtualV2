/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.leilaoservidormulticast.compartilhado.domain;

import com.mycompany.leilaoservidormulticast.compartilhado.dtos.LanceDto;
import com.mycompany.leilaoservidormulticast.compartilhado.dtos.StreamDto;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.io.Serializable;

/**
 *
 * @author skmat
 */
public class Leiloeiro implements Serializable {

    private transient MulticastSocket socket;
    private Leilao auction;

    public Leiloeiro(Leilao auction) {
        try {
            this.auction = auction;
            socket = new MulticastSocket(auction.getPorta());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void joinAuction() {
        try {
            socket.joinGroup(auction.getEndereco());
            auction.setStatus(Leilao.INICIADO);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void leaveAuction() {
        try {
            sendEndAuction();
            socket.leaveGroup(auction.getEndereco());
            auction.setStatus(Leilao.FINALIZADO);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void listenLance(Runnable callback) {
        new Thread(() -> {
            while (true) {
                try {
                    byte[] incomingData = new byte[1024];
                    DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                    socket.receive(incomingPacket);
                    
                    ByteArrayInputStream inputBytes = new ByteArrayInputStream(incomingPacket.getData());
                    ObjectInputStream inputObject = new ObjectInputStream(inputBytes);
                    
                    StreamDto data = (StreamDto) inputObject.readObject();
                    LanceDto lanceRequisicao = (LanceDto) data.getPayload();
                    
                    if (data.getTipo() == StreamDto.REQUISICAO_LANCE) {
                        if (lanceRequisicao.getPreco() > auction.getUltimoLance().getPreco()) {
                            auction.setUltimoLance(lanceRequisicao);
                            enviarUltimoLance();
                            callback.run();
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
    }

    public void enviarUltimoLance() {
        try {
            ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
            ObjectOutputStream outputObject = new ObjectOutputStream(outputBytes);

            outputObject.writeObject(new StreamDto(StreamDto.RESPOSTA_LANCE, auction.getUltimoLance()));
            byte[] bytesData = outputBytes.toByteArray();
            DatagramPacket packet = new DatagramPacket(bytesData, bytesData.length, auction.getEndereco(), auction.getPorta());
            socket.send(packet);
            System.out.println("Envio do ultimo lance");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void sendEndAuction() {
        try {
            ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
            ObjectOutputStream outputObject = new ObjectOutputStream(outputBytes);

            outputObject.writeObject(new StreamDto(StreamDto.LEILAO_TERMINO));
            byte[] bytesData = outputBytes.toByteArray();
            DatagramPacket packet = new DatagramPacket(bytesData, bytesData.length, auction.getEndereco(), auction.getPorta());
            socket.send(packet);
            System.out.println("Envio do ultimo lance"); // enviar ultima action
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
