
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface UsuarioServiceInterface extends java.rmi.Remote {

    public List<Usuario> getAll() throws java.rmi.RemoteException;
    public Usuario getById(Long id) throws java.rmi.RemoteException;
    public Usuario addOne(Usuario usuario) throws java.rmi.RemoteException;

}
