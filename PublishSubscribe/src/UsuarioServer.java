
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author guest-u1pwic
 */
public class UsuarioServer {


    public UsuarioServer() {
        try {
            System.out.println("UsuarioServer");
            UsuarioServiceInterface u = new UsuarioService();
            Naming.rebind("//127.0.0.1:1099/UsuarioService", u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new UsuarioServer();
    }

}
