package base;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

/**
 * @author guest-u1pwic
 */
public class UsuarioService extends java.rmi.server.UnicastRemoteObject implements UsuarioServiceInterface {

    private List<Usuario> listUsuarios = new ArrayList<>();

    protected UsuarioService() throws RemoteException {
        super();
    }

    @Override
    public Usuario getById(Long id) {
        return listUsuarios
                .stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst()
                .get();
    }

    @Override
    public List<Usuario> getAll() {
        return listUsuarios;
    }

    @Override
    public Usuario addOne(Usuario usuario) {
        System.out.println("UsuarioService.java - Adicionando usuario " + usuario.toString());
        listUsuarios.add(usuario);
        return usuario;
    }


}



