import java.rmi.Naming;

public class Publisher {
    public static void main(String[] args) {
        System.out.println("Publisher");
        Publisher instancia = new Publisher();
        instancia.setUpServer();
    }

    public void setUpServer() {
        try {
            System.out.println("Publisher - setUpServer");
            PublisherServiceInterface api = new PublisherService();
            Naming.rebind("//127.0.0.1:1099/Publisher", api);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Publisher() {
    }
}
