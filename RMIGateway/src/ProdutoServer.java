
import java.rmi.Naming;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author guest-u1pwic
 */
    public class ProdutoServer {


        public ProdutoServer() {
            try {
                System.out.println("ProdutoServer");
                ProdutoServiceInterface u = new ProdutoService();
                Naming.rebind("//127.0.0.1:1099/ProdutoService", u);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void main(String args[]) {
            new ProdutoServer();
        }

    }