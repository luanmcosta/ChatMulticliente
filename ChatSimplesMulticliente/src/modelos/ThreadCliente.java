package modelos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Servidor;

public class ThreadCliente implements Runnable{

    Socket cliente;
    Servidor servidor;
    ObjectInputStream entradaDados;
    ObjectOutputStream saidaDados;

    public ThreadCliente(Socket cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }
    
    @Override
    public void run() {
                     
        try {
            // Realizar conexão com os canais de transmissão do cliente
            saidaDados = new ObjectOutputStream(cliente.getOutputStream());
            entradaDados = new ObjectInputStream(cliente.getInputStream());
            
            // Laço infinito de requisições
            while(true){
                // Receber dados da mensagem
                Mensagem mensagem = (Mensagem) entradaDados.readObject();
                
                // Imprimir requisição
                System.out.println(mensagem.getRemetente() + ": " + mensagem.getTexto());
                
                // Enviar pros clientes
                servidor.enviarParaTodos(mensagem);

            }         
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    // Métodos

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public ObjectInputStream getEntradaDados() {
        return entradaDados;
    }

    public void setEntradaDados(ObjectInputStream entradaDados) {
        this.entradaDados = entradaDados;
    }

    public ObjectOutputStream getSaidaDados() {
        return saidaDados;
    }

    public void setSaidaDados(ObjectOutputStream saidaDados) {
        this.saidaDados = saidaDados;
    }
 
    
}
