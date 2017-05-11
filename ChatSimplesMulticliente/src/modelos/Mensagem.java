package modelos;

import java.io.Serializable;

public class Mensagem implements Serializable{
 
    // Atributos
    private String remetente;
    private String texto;
    
    // Construtor(es)
    public Mensagem() {
    }
     
    public Mensagem(String remetente, String texto) {
        this.remetente = remetente;
        this.texto = texto;
    }
    
    public Mensagem(Mensagem msg){
        this.remetente = msg.getRemetente();
        this.texto = msg.getTexto();
    }
    
    // Métodos Getters e Setters
    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    // Métodos Especialistas
    
    
}
