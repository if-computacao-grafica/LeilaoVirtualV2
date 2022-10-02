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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author skmat
 */
public class LeilaoClienteUnicast {
    public static void main(String[] args) throws NoSuchAlgorithmException {        
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

    private HashMap<Integer, Cliente> clientes;
    private Cliente clienteAtual = null;
    private IProtocoloRMI _rmi;

    private void startInput() {
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

                this.clientes = _rmi.getClientes();
                switch(entrada[0].toLowerCase()) {
                    case "conta":
                        switch(entrada[1].toLowerCase()) {
                            case "entrar":
                                if (clientes.isEmpty()) {
                                    System.out.println("Nao ha contas. Crie contas com `conta/criar`");
                                    break;
                                }
                                if (entrada.length < 4) {
                                    System.out.println("Para entrar no servidor utilize: `conta/entrar/{idCliente}/{chavePublica}`.");
                                    break;
                                } else {

                                    Cliente clienteAtual = clientes.get(Integer.parseInt(entrada[2]));
                                    clienteAtual.solicitarEntrada(entrada[3], clienteAtual); //OK
                                    if(clientes.get(Integer.parseInt(entrada[2])).getAutorizado()){ //OK
                                        byte[] endereco = _rmi.solicitarEnderecosMulticast(clienteAtual.getId());
                                        byte[] chaveAES = _rmi.solicitarChaveAES(clienteAtual.getId());
                                        byte[] enderecoo = CriptografiaUtils.decifrarTexto(clienteAtual.getChavePrivada(), endereco);
                                        byte[] chaveAESS = CriptografiaUtils.decifrarTexto(clienteAtual.getChavePrivada(), chaveAES);
                                        
                                        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(enderecoo));
                                        try {
                                            @SuppressWarnings("unchecked")
                                            ArrayList<String> enderecos = (ArrayList<String>) ois.readObject();
                                            for (String enderecoos : enderecos) {
                                                System.out.println("Endereco Multicast: " + enderecoos);    
                                            }
                                        } finally {
                                            ois.close();
                                        }
                                        //converting byte to String 
                                        String str_key = Base64.getEncoder().encodeToString(chaveAESS);
                                        System.out.println("Chave AES: " + str_key);
                                        
                                    }
                                    if (!clienteAtual.getAutorizado()) System.out.println("Nao foi possivel autorizar Cliente`.");
                                }
                                break;
                            case "criar":
                                if (entrada.length < 3) {
                                    System.out.println("Para criar contas utilize: `conta/criar/{nome}`");
                                    break;
                                }
                                Integer idGerado = 0;
                                for (int i = 0; i < clientes.size(); ++i) {
                                    if (clientes.get(i).getId() == i) idGerado++;
                                    else break;
                                }
                                Cliente cliente = new Cliente(idGerado, entrada[2]);
                                clienteAtual = cliente;
                                _rmi.addCliente(clienteAtual);
                                System.out.println("Cliente criado com ID: "+idGerado+".");
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

