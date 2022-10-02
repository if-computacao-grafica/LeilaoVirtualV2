package com.mycompany.leilaoservidorunicast.rmi;

import java.io.Serializable;
import java.security.PublicKey;

public interface IServidor extends Serializable {
    public Integer getId();
    public PublicKey getChavePublica();

    public byte[] obterMensagemCifrada(byte[] message);
}
