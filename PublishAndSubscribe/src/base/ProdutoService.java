package base;

import java.rmi.RemoteException;
import java.util.*;

/**
 *
 * @author guest-u1pwic
 */
public class ProdutoService extends java.rmi.server.UnicastRemoteObject implements ProdutoServiceInterface {

    private List<Produto> listProdutos = new ArrayList<>();

    protected ProdutoService() throws RemoteException {
        super();
    }

    @Override
    public Produto getById(Long id) {
        return listProdutos
                .stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst()
                .get();
    }

    @Override
    public List<Produto> getAll() {
        return listProdutos;
    }

    @Override
    public Produto addOne(Produto produto) {
        System.out.println("ProdutoService.java - Adicionando produto " + produto.toString());
        listProdutos.add(produto);
        return produto;
    }


}
