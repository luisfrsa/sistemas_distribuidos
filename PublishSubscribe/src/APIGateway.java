import java.rmi.Naming;
import java.util.List;

public class APIGateway {

    public static void main(String[] args) {
        System.out.println("API Gateway");
        APIGateway instancia = new APIGateway();
        instancia.setUpServer();
    }

    public void setUpServer() {
        try {
            System.out.println("API Gateway - setUpServer");
            APIGatewayServiceInterface api = new APIGatewayService();
            Naming.rebind("//127.0.0.1:1099/APIGateway", api);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public APIGateway() {
    }


}
