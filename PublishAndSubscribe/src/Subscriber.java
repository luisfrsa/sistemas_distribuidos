
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Subscriber {

    List<String> interesetList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Subscriber");
        Subscriber instancia = new Subscriber();
        instancia.setUpServer();
    }

    public void setUpServer() {
        try {
            System.out.println("Subscriber - setUpServer");
            SubscriberServiceInterface api = new SubscriberService();
            Naming.rebind("//127.0.0.1:1099/Subscriber", api);//disponibiliza esta url para acessoa ele (Como servidor)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Subscriber() {
    }
   

}
