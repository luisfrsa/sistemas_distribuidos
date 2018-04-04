
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author guest-u1pwic
 */
public class UsuarioService extends java.rmi.server.UnicastRemoteObject {

    private Set<Usuario> listUsuarios;

    public UsuarioService() throws java.rmi.RemoteException {
    }

    private Usuario getByNome(String nome) {
        Optional<Usuario> usuario = listUsuarios
                .stream()
                .filter(u -> Objects.equals(u.getNome(), nome))
                .findFirst();

        return usuario.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

    }

    private Set<Usuario> getAllUsuarios() {
        listUsuarios.clear();
        listUsuarios.add(new Usuario(1L, "luis"));
        listUsuarios.add(new Usuario(2L, "ana"));
        listUsuarios.add(new Usuario(3L, "joao"));
        listUsuarios.add(new Usuario(4L, "roberto"));
        listUsuarios.add(new Usuario(5L, "maria"));
        return listUsuarios;
    }
}
