import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author guest-u1pwic
 */
public class APIGatewayService extends java.rmi.server.UnicastRemoteObject implements APIGatewayServiceInterface {

    private List<Usuario> listUsuarios;
    private List<Produto> listProdutos;
    private static UsuarioServiceInterface usuarioService = null;
    private static ProdutoServiceInterface produtoService = null;

    protected APIGatewayService() throws RemoteException {
        super();
    }


    private UsuarioServiceInterface getUsuarioServiceInstance() {
        try {
            if (Objects.isNull(usuarioService)) {
                usuarioService = (UsuarioServiceInterface) Naming.lookup("//127.0.0.1:1099/UsuarioService");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioService;
    }

    private ProdutoServiceInterface getProdutoServiceInstance() {
        try {
            if (Objects.isNull(produtoService)) {
                produtoService = (ProdutoServiceInterface) Naming.lookup("//127.0.0.1:1099/ProdutoService");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtoService;
    }


    @Override
    public List<Usuario> getAllUsuarios() throws RemoteException {
        usuarioService = getUsuarioServiceInstance();
        return usuarioService.getAll();
    }

    @Override
    public Usuario getUsuarioById(Long id) throws RemoteException {
        System.out.println("APIGatewayService.java - Buscando usuario pelo id " + id);
        usuarioService = getUsuarioServiceInstance();
        return usuarioService.getById(id);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) throws RemoteException {
        usuarioService = getUsuarioServiceInstance();
        System.out.println("APIGatewayService.java - Adicionando usuario " + usuario.toString());
        return usuarioService.addOne(usuario);
    }

    @Override
    public List<Produto> getAllProdutos() throws RemoteException {
        produtoService = getProdutoServiceInstance();
        return produtoService.getAll();
    }

    @Override
    public Produto getProdutoById(Long id) throws RemoteException {
        System.out.println("APIGatewayService.java - Buscando produto pelo id " + id);
        produtoService = getProdutoServiceInstance();
        return produtoService.getById(id);
    }

    @Override
    public Produto addProduto(Produto produto) throws RemoteException {
        produtoService = getProdutoServiceInstance();
        System.out.println("APIGatewayService.java - Adicionando produto " + produto.toString());
        return produtoService.addOne(produto);
    }

}



