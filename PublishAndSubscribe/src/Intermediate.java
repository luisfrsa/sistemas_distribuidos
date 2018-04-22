import java.rmi.Naming;

public class Intermediate {

    public static void main(String[] args) {
        System.out.println("Intermediate");
        Intermediate instancia = new Intermediate();
        instancia.setUpServer(args[0]);
//        instancia.setUpServer("Intermediate");
    }

    public void setUpServer(String name) {
        try {
            System.out.println("Intermediate - settingUpServer: "+"//127.0.0.1:1099/"+name);
            IntermediateServiceInterface connect = new IntermediateService();
            Naming.rebind("//127.0.0.1:1099/"+name, connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Intermediate() {
    }
}
