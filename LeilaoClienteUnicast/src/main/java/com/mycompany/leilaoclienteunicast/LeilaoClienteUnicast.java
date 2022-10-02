/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.leilaoclienteunicast;

import com.mycompany.leilaoservidorunicast.domain.Cliente;
import com.mycompany.leilaoservidorunicast.rmi.IProtocoloRMI;
import com.mycompany.leilaoservidorunicast.util.CriptografiaUtils;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author skmat
 */
public class LeilaoClienteUnicast {
    public static void main(String[] args) throws NoSuchAlgorithmException, RemoteException {        
        LeilaoClienteUnicast cliente = new LeilaoClienteUnicast();
        cliente.startInput();
    }

    public LeilaoClienteUnicast() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            _rmi = (IProtocoloRMI) registry.lookup("ServidorRMI"); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    private Cliente clienteAtual = null;
    private IProtocoloRMI _rmi;

    private void startInput() throws RemoteException {
        try {
            System.out.println("Ola Cliente!\nDigite `ajuda` para ver os comandos.");
            Scanner scanner = new Scanner(System.in);

            // Laço para ouvir o que o usuário solicita
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] entrada = input.split("/");
                if (entrada.length < 2) {
                    System.out.println("### Comandos disponiveis ###");
                    System.out.println("conta/criar");
                    System.out.println("conta/entrar");
                    continue;
                }

                switch(entrada[0].toLowerCase()) {
                    case "conta":
                        switch(entrada[1].toLowerCase()) {
                            case "entrar":
                                if (entrada.length < 4) {
                                    System.out.println("Para entrar no servidor utilize: `conta/entrar/{endereco}/{nome}`.");
                                    break;
                                } else {
                                    _rmi.informarChavePublica(entrada[2]);
                                    PrivateKey chavePrivada = CriptografiaUtils.lerChavePrivada("C:\\chaves\\chavesPrivadas\\"+entrada[3]);

                                    byte[] enderecoCifrado = _rmi.getEnderecoMulticast();
                                    byte[] chaveCifrada = _rmi.getChaveSimetrica();
                                    byte[] enderecoDecifrado = CriptografiaUtils.decifrarTexto(chavePrivada, enderecoCifrado);
                                    byte[] chaveDecifrada = CriptografiaUtils.decifrarTexto(chavePrivada, chaveCifrada);
                                    
                                    // Reconstrução de chaveDecifrada em texto
                                    // Base64.getDecoder().decode(chaveDecifrada);
                                    // new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
                                    
                                    // Representação da chave simetrica em texto
                                    System.out.print("Chave Multicast: ");
                                    System.out.println(Base64.getEncoder().encodeToString(chaveDecifrada));
                                    
                                    System.out.print("Endereco Multicast: ");
                                    System.out.println(Base64.getEncoder().encodeToString(enderecoDecifrado));
                                }
                            break;
                            case "criar":
                                if (entrada.length < 3) {
                                    System.out.println("Para criar contas utilize: `conta/criar/{nome}`");
                                    break;
                                }
                                Cliente cliente = new Cliente(entrada[2]);
                                clienteAtual = cliente;
                                _rmi.addCliente(clienteAtual);
                                System.out.println("Cliente" +entrada[2]+ "gerarado.");
                                break;
                        }
                        break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

