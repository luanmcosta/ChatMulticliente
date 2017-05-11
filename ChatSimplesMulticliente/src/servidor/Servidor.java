package servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Mensagem;
import modelos.ThreadCliente;

public class Servidor {

    // Array de Clientes 
    ArrayList<ThreadCliente> clientes = new ArrayList();
    
    Socket console;
    
    public void iniciarServidor() {
        
        System.out.println("Servidor Iniciado!");

        // Socket do Servidor
        ServerSocket servidor;

        // Socket(conexao) com o cliente
        Socket cliente;

        // Declaração da mensagem
        Mensagem mensagem;

        try {
            // Tentar criar conexão como servidor
            servidor = new ServerSocket(20171);

            // Laço infinito no aguardo de clientes
            while(true){
                // Esperar conexão do usuário
                cliente = servidor.accept();

                // Aqui tenho que executar a thread
                ThreadCliente tc = new ThreadCliente(cliente, this);

                // Adicionar cliente à fila
                clientes.add(tc);

                // Iniciar thread do cliente
                new Thread(tc).start();
            }  
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }
    
    public void enviarParaTodos(Mensagem msg){
        
        // Percorrer todas os clientes conectados
        for(ThreadCliente cliente : clientes){
            // Tentar enviar mensagem
            if(cliente.getCliente() == null)
                continue;
            
            try {
                cliente.getSaidaDados().writeObject(msg);
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
