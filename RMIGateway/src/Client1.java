import java.rmi.Naming;
import java.util.List;

public class Client1 {
    public static void main(String[] args) {
        try {
            APIGatewayServiceInterface apiGateway = (APIGatewayServiceInterface) Naming.lookup("//127.0.0.1:1099/APIGateway");
            List<Usuario> listUsuario = apiGateway.getAllUsuarios();
            System.out.println("Client!");
            listUsuario.forEach(u -> {
                System.out.println(u.toString());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
