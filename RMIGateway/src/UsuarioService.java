
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

/**
 * @author guest-u1pwic
 */
public class UsuarioService extends java.rmi.server.UnicastRemoteObject implements UsuarioServiceInterface {

    private List<Usuario> listUsuarios;

    protected UsuarioService() throws RemoteException {
        super();
        listUsuarios = new ArrayList<>();
    }

    public Usuario getById(Long id) {
        Optional<Usuario> usuario = listUsuarios
                .stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst();
        return usuario.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public List<Usuario> getAll() {
        if (listUsuarios.size() == 0) {
            listUsuarios = initList();
        }
        return listUsuarios;
    }


    private List<Usuario> initList() {
        listUsuarios = new ArrayList<>();
        listUsuarios.add(new Usuario(2L, "ana"));
        listUsuarios.add(new Usuario(2L, "ana"));
        listUsuarios.add(new Usuario(3L, "joao"));
        listUsuarios.add(new Usuario(4L, "roberto"));
        listUsuarios.add(new Usuario(5L, "maria"));
        return listUsuarios;
    }

}



