import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientUsuario {

    private APIGatewayServiceInterface apiGateway;

    public static void main(String[] args) {
        try {
            APIGatewayServiceInterface apiGateway = (APIGatewayServiceInterface) Naming.lookup("//127.0.0.1:1099/APIGateway");
            ClientUsuario self = new ClientUsuario(apiGateway);

            System.out.println("\n--------Criando usuarios--------");
            self.createUsers();

            System.out.println("\n--------Get usuarios--------");
            self.getUserById(1L);
            self.getUserById(2L);
            self.getUserById(3L);

            System.out.println("\n--------Get All usuarios--------");
            self.getAllUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ClientUsuario(APIGatewayServiceInterface apiGateway) {
        this.apiGateway = apiGateway;
    }

    private void createUsers() {
        List<Usuario> listUsuarios = new ArrayList<>();
        listUsuarios.add(new Usuario(1L, "ana"));
        listUsuarios.add(new Usuario(2L, "paulo"));
        listUsuarios.add(new Usuario(3L, "joao"));
        listUsuarios.forEach(usuario -> {
            try {
                System.out.println("ClientUsuario.java - Adicionando usuario " + usuario.toString());
                apiGateway.addUsuario(usuario);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }


    private void getUserById(Long id) throws RemoteException {
        System.out.println("ClientUsuario.java - Buscando usuario pelo id " + id);
        Usuario usuario = apiGateway.getUsuarioById(id);
        System.out.println("ClientUsuario.java - Usuario encontrado pelo pelo id " + usuario.toString());
    }

    private void getAllUsers() throws RemoteException {
        List<Usuario> listUsuarios = apiGateway.getAllUsuarios();
        listUsuarios.forEach(usuario -> {
            System.out.println("ClientUsuario.java - Printando todos os usuarios " + usuario.toString());
        });

    }
}
