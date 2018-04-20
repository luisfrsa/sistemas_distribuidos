package base;

import java.util.List;

public interface UsuarioServiceInterface extends java.rmi.Remote {

    public List<Usuario> getAll() throws java.rmi.RemoteException;
    public Usuario getById(Long id) throws java.rmi.RemoteException;
    public Usuario addOne(Usuario usuario) throws java.rmi.RemoteException;

}
