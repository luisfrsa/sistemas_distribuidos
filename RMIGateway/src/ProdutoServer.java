
import java.rmi.Naming;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author guest-u1pwic
 */
public class ProdutoServer {
        
    
    public static void main(String args[]) {
        new ProdutoServer();
    }
    

    public ProdutoServer() {
        try {
            Naming.rebind("//127.0.0.1:1099/ProdutoService", new ProdutoService());
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
    
   
}
