
import java.rmi.RemoteException;
import java.util.List;

public interface APIGatewayServiceInterface extends java.rmi.Remote {

    public List<Usuario> getAllUsuarios() throws RemoteException;
    public Usuario getUsuarioById(Long id) throws RemoteException;
    public Usuario addUsuario(Usuario usuario) throws RemoteException;

    public List<Produto> getAllProdutos() throws RemoteException;
    public Produto getProdutoById(Long id) throws RemoteException;
    public Produto addProduto(Produto produto) throws RemoteException;

}
