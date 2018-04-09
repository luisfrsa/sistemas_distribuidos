import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Client1 {

    private APIGatewayServiceInterface apiGateway;

    public static void main(String[] args) {
        try {
            APIGatewayServiceInterface apiGateway = (APIGatewayServiceInterface) Naming.lookup("//127.0.0.1:1099/APIGateway");
            Client1 self = new Client1(apiGateway);
            self.createUsers();
            self.getUserById(1L);
            self.getAllUsers();

//            List<Usuario> listUsuario = apiGateway.getAllUsuarios();
//            System.out.println("Client!");
//            listUsuario.forEach(u -> {
//                System.out.println(u.toString());
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Client1(APIGatewayServiceInterface apiGateway) {
        this.apiGateway = apiGateway;
    }

    private void createUsers() {
        List<Usuario> listUsuarios = new ArrayList<>();
        listUsuarios.add(new Usuario(2L, "ana"));
        listUsuarios.add(new Usuario(2L, "ana"));
        listUsuarios.add(new Usuario(3L, "joao"));
        listUsuarios.add(new Usuario(4L, "roberto"));
        listUsuarios.add(new Usuario(5L, "maria"));
        listUsuarios.forEach(usuario -> {
            System.out.println("Client1.java - Adicionando usuario " + usuario.toString());
            try {
                apiGateway.addUsuario(usuario);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }


    private void getUserById(Long id) {
        Usuario usuario = apiGateway.getUsuarioById(id);
        System.out.println("Client1.java - Buscando usuario pelo id " + usuario.toString());
    }

    private void getAllUsers() throws RemoteException {
        List<Usuario> listUsuarios = apiGateway.getAllUsuarios();
        listUsuarios.forEach(usuario -> {
            System.out.println("Client1.java - Printando todos os usuarios " + usuario.toString());
        });

    }
}
