package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import modelos.Mensagem;
import view.cliente.TelaCliente;

public class Cliente {
    
    // Atributos
    private String nomeCliente;
    private Mensagem mensagem;
    
    // Config do servidor
    private String servidor;
    private int porta;
    
    // Canais de Entrada e Saida
    ObjectOutputStream saidaDados;
    ObjectInputStream entradaDados;
    
    // Tela
    TelaCliente tc;
    
    // Socket(conexao) com o servidor
    Socket cliente_servidor;

    public void iniciarCliente() { 

        // Declarar e Instanciar Operação
        mensagem = new Mensagem();
        
        // Obter informação do cliente
        nomeCliente = JOptionPane.showInputDialog("Digite seu nome.");
        mensagem.setRemetente(nomeCliente);
        
        // Iniciar Tela do Cliente
        tc = new TelaCliente(this, nomeCliente);
        tc.setVisible(true);
        
        // Conectar ao servidor
        if(!conectar(this.servidor, this.porta))
            return;

    }
    
    public void enviarMensagem(String msg){
        try {
            mensagem.setTexto(msg);
            saidaDados.writeObject(new Mensagem(mensagem));
        } catch (IOException ex) {
            System.out.println("Erro ao tentar enviar mensagem!");
        }
    }
    
    public boolean conectar(String servidor, int porta){
        try {
            //Tentar realizar conexão com o servidor
            cliente_servidor = new Socket(servidor, porta);
            
            // Realizar conexão com os canais de transmissão
            saidaDados = new ObjectOutputStream(cliente_servidor.getOutputStream());
            entradaDados = new ObjectInputStream(cliente_servidor.getInputStream());
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    // Métodos getters e setters
    public Socket getCliente_servidor() {
        return cliente_servidor;
    }

    public void setCliente_servidor(Socket cliente_servidor) {
        this.cliente_servidor = cliente_servidor;
    }

    public ObjectOutputStream getSaidaDados() {
        return saidaDados;
    }

    public void setSaidaDados(ObjectOutputStream saidaDados) {
        this.saidaDados = saidaDados;
    }

    public ObjectInputStream getEntradaDados() {
        return entradaDados;
    }

    public void setEntradaDados(ObjectInputStream entradaDados) {
        this.entradaDados = entradaDados;
    }
    
    
    
}
