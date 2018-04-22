import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Publisher {

    private String name;
    private List<String> publishList;
    private List<IntermediateServiceInterface> intermediateServiceList;

    public static void main(String[] args) {
        System.out.println("Publisher");
        Publisher self = new Publisher();
        self.setName(args[0]);
        self.intermediateServiceList = self.createIntermediateList(args[1]);
        self.createPublishList(args[2]);
        self.publishData();
        doneAndWaitEnter();

    }

    public Publisher() {
    }

    public void publishData() {
        System.out.print("Publicando de: " + name + " os seguintes itens " + publishList.toString());
        intermediateServiceList.forEach(intermediate -> {
            try {
                System.out.print("Para: "+intermediate.getName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                intermediate.receivePublishFromPublisher(publishList);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        System.out.println("");
    }

    private List<IntermediateServiceInterface> createIntermediateList(String name) {
        System.out.println("Criando lista de Intermediarios " + name);
        List<IntermediateServiceInterface> intermediateServiceList = new ArrayList<>();
        try {
            IntermediateServiceInterface intermediateService = (IntermediateServiceInterface) Naming.lookup("//127.0.0.1:1099/" + name);
            intermediateServiceList.add(intermediateService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intermediateServiceList;
    }

    private void createPublishList(String args) {
        this.publishList = Arrays.asList(args.split(","));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(name, publisher.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    private static void doneAndWaitEnter() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        System.out.println("Iniciando Publisher "+name);
        this.name = name;
    }
}
