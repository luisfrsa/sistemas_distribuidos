
import java.rmi.RemoteException;
import java.util.List;

public interface APIGatewayServiceInterface extends java.rmi.Remote {

    public List<Usuario> getAllUsuarios() throws RemoteException;
    public Usuario getUsuarioById(Long id) throws RemoteException;

}
