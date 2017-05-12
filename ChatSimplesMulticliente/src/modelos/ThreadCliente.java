package modelos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        
        // Auxiliares para obter hora:minuto
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date hora;
        String horaString;
     
                     
        try {
            // Realizar conexão com os canais de transmissão do cliente
            saidaDados = new ObjectOutputStream(cliente.getOutputStream());
            entradaDados = new ObjectInputStream(cliente.getInputStream());
            
            // Laço infinito de requisições
            while(true){
                if(cliente.isClosed())
                    break;
                
                // Receber dados da mensagem
                Mensagem mensagem = (Mensagem) entradaDados.readObject();
                   
                // Imprimir requisição
                System.out.println(mensagem.getRemetente() + ": " + mensagem.getTexto());
                
                // Enviar pros clientes
                servidor.enviarParaTodos(mensagem);
                
                // Extrair hora
                hora = Calendar.getInstance().getTime();
                horaString = sdf.format(hora);
                
                // Salvar mensagem
                salvarMensagem("log.txt", ("["+ horaString + "]" + mensagem.getRemetente() + ": " + mensagem.getTexto()));

            }         
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Um cliente se desconectou!");
            Thread.currentThread().stop();
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
 
    public void salvarMensagem(String arquivo, String msg){
        try {
            FileWriter arq = new FileWriter(arquivo, true);
            arq.write(msg);
            arq.write(System.lineSeparator());
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
