
import java.rmi.Naming;

public class CompraServer {

    public static void main(String args[]) {
        new UsuarioServer();
    }

    public CompraServer() {
        try {
            Naming.rebind("//127.0.0.1:1099/CompraService", new CompraService());
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

}
