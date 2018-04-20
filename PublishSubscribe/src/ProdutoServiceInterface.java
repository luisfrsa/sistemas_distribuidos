
import java.rmi.RemoteException;
import java.util.List;

public interface ProdutoServiceInterface extends java.rmi.Remote {

    public List<Produto> getAll() throws RemoteException;
    public Produto getById(Long id) throws RemoteException;
    public Produto addOne(Produto produto) throws RemoteException;

}
