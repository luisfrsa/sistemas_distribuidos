
import java.rmi.Naming;



public class CalculatorServer {

    public CalculatorServer() {
        try {
            Calculator c = new CalculatorImpl();
            Naming.rebind("//127.0.0.1:1099/CalculatorService", c);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new CalculatorServer();
    }
}
