package cliente;

public class ClienteApp {


    public static void main(String[] args) {
        
        Cliente c = new Cliente();
        c.iniciarCliente();
        c.conectar("localhost", 20171);
        
    }
    
}
