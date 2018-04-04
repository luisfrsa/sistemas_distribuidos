
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guest-u1pwic
 */
public class UsuarioServer extends java.rmi.server.UnicastRemoteObject{

    public static void main(String args[])   {
        try {
            new UsuarioServer();
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UsuarioServer() throws java.rmi.RemoteException{
        try {
            Naming.rebind("//127.0.0.1:1099/UsuarioService", new UsuarioService());
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

}
