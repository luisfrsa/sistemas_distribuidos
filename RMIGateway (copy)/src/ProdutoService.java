
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author guest-u1pwic
 */
public class ProdutoService extends java.rmi.server.UnicastRemoteObject {

    private Set<Produto> listaProdutos;

    public ProdutoService() throws java.rmi.RemoteException{
    }

    private Produto getById(Long id) {
        Optional<Produto> produto = listaProdutos
                .stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();

        return produto.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    }

    private Set<Produto> getAllProdutos() {
        listaProdutos.clear();
        listaProdutos.add(new Produto(1L, "Celular", 1800.00));
        listaProdutos.add(new Produto(2L, "Computador", 2500.00));
        listaProdutos.add(new Produto(3L, "Mouse", 50.00));
        listaProdutos.add(new Produto(4L, "Teclado", 120.00));
        listaProdutos.add(new Produto(5L, "Monitor", 600.00));
        return listaProdutos;
    }
}
