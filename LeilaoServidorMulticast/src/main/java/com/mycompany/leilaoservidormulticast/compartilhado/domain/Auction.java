/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.leilaoservidormulticast.compartilhado.domain;

import com.mycompany.leilaoservidormulticast.compartilhado.dtos.LanceDto;
import com.mycompany.leilaoservidormulticast.utils.CriptografiaUtils;
import java.io.Serializable;
import java.net.InetAddress;
import javax.crypto.SecretKey;

/**
 *
 * @author skmat
 */
public class Auction implements Serializable {
    public static final int NAO_INICIADO = 1;    
    public static final int INICIADO = 2;
    public static final int FINALIZADO = 3;
    
    private final Produto produto;
    private LanceDto ultimoLance;
    private final InetAddress endereco;
    private final int porta;
    private final Leiloeiro leiloeiro;
    private int status = Auction.NAO_INICIADO;
    private final SecretKey chaveSimetrica;

    public Auction(Produto produto, InetAddress endereco, int porta) {
        this.produto = produto;
        this.ultimoLance = new LanceDto(produto.getPreco());
        this.endereco = endereco;
        this.porta = porta;
        this.leiloeiro = new Leiloeiro(this);
        this.chaveSimetrica = CriptografiaUtils.gerarChave();

        System.out.print("Criação endereco leilao: ");
        System.out.println(endereco.getHostAddress());
    }
    
    public void iniciarAuction(Runnable callback) {
        leiloeiro.joinAuction();
        leiloeiro.listenLance(callback);
    }
    
    public void pararAuction() {
        leiloeiro.leaveAuction();
    }

    public Produto getProduto() {
        return produto;
    }

    public LanceDto getUltimoLance() {
        return ultimoLance;
    }

    public void setUltimoLance(LanceDto ultimoLance) {
        this.ultimoLance = ultimoLance;
    }

    public InetAddress getEndereco() {
        //CriptografiaUtils.cifrarTexto(chavePublica,
        //                              endereco.getEndereco().toString().getBytes());
        return endereco;
    }

    public int getPorta() {
        return porta;
    }
    
    public Leiloeiro getLeiloeiro() {
        return leiloeiro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SecretKey getChaveSimetrica() {
        return chaveSimetrica;
    }

}
