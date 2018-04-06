import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author guest-u1pwic
 */
public class APIGatewayService extends java.rmi.server.UnicastRemoteObject implements APIGatewayServiceInterface {

    private List<Usuario> listUsuarios;
    private static UsuarioServiceInterface usuarioService = null;

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


    @Override
    public List<Usuario> getAllUsuarios() throws RemoteException {
        usuarioService = getUsuarioServiceInstance();
        return usuarioService.getAll();
    }

    @Override
    public Usuario getUsuarioById(Long id) throws RemoteException {
        usuarioService = getUsuarioServiceInstance();
        return usuarioService.getById(id);
    }

}



