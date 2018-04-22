
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subscriber {

    public static void main(String[] args) {
        System.out.println("Subscriber");
        Subscriber instancia = new Subscriber();
        instancia.setUpServer(args[0]);
//        instancia.setUpServer("Subscriber");
    }

    public void setUpServer(String name) {
        try {
            System.out.println("Subscriber - settingUpServer: "+"//127.0.0.1:1099/"+name);
            SubscriberServiceInterface connect = new SubscriberService();
            Naming.rebind("//127.0.0.1:1099/"+name, connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Subscriber() {
    }
}
