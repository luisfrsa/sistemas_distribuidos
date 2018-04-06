import java.rmi.Naming;
import java.util.List;

public class APIGatewayCliServ {

    public static void main(String[] args) {
        APIGatewayCliServ instancia= new APIGatewayCliServ();
        instancia.getAllUsuarios();
    }

    public APIGatewayCliServ() {
    }

    public List<Usuario> getAllUsuarios(){
        try {
            UsuarioServiceInterface usuarioService = (UsuarioServiceInterface) Naming.lookup("//127.0.0.1:1099/UsuarioService");
            return usuarioService.getAll();
//            usuarioService.getAll().forEach(e -> {
//                System.out.println(e);
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
