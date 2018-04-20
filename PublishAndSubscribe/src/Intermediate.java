import java.rmi.Naming;

public class Intermediate {
    public static void main(String[] args) {
        System.out.println("Intermediate");
        Intermediate instancia = new Intermediate();
        instancia.setUpServer();
    }

    public void setUpServer() {
        try {
            System.out.println("Intermediate - setUpServer");
            IntermediateServiceInterface api = new IntermediateService();
            Naming.rebind("//127.0.0.1:1099/Intermediate", api);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Intermediate() {
    }
}
