
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author guest-u1pwic
 */
public class SubscriberService extends java.rmi.server.UnicastRemoteObject implements SubscriberServiceInterface {

    String name;
    List<String> subscribeList = new ArrayList<>();
    List<String> receivedMessage = new ArrayList<>();
    List<IntermediateServiceInterface> intermediateServiceList = new ArrayList<>();

    protected SubscriberService() throws RemoteException {
        super();
        List<String> interesetList = createSubscribeList();
    }


    public void subscribeData() {
        System.out.println("Iniciando Assinatura de: " + subscribeList.toString());
        intermediateServiceList.forEach(intermediate -> {
            try {
                intermediate.receiveSubscribeFromSubscriber(this, subscribeList);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void receivePublishedMessage(String published) throws RemoteException {
        if (!receivedMessage.contains(published)) {
            receivedMessage.add(published);
            System.out.println(".::Mensagem publicada recebida: " + published + "::.");
        }
    }

    public List<IntermediateServiceInterface> createIntermediateList(String name) {
        List<IntermediateServiceInterface> intermediateServiceList = new ArrayList<>();
        try {
            IntermediateServiceInterface intermediateService = (IntermediateServiceInterface) Naming.lookup("//127.0.0.1:1099/" + name);
            intermediateServiceList.add(intermediateService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intermediateServiceList;
    }

    private List<String> createSubscribeList() {
        List<String> publishList = new ArrayList<>();
        publishList.add("Notícia");
        publishList.add("Esporte");
        publishList.add("Dinheiro");
        return publishList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriberService)) return false;
        if (!super.equals(o)) return false;
        SubscriberService that = (SubscriberService) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }


    @Override
    public void setName(String name) {
        System.out.println("Name has been setted to " + name);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public List<IntermediateServiceInterface> getIntermediateServiceList() {
        return intermediateServiceList;
    }

    public void setIntermediateServiceList(List<IntermediateServiceInterface> intermediateServiceList) {
        this.intermediateServiceList = intermediateServiceList;
    }

    public List<String> getSubscribeList() {
        return subscribeList;
    }

    public void setSubscribeList(List<String> subscribeList) {
        this.subscribeList = subscribeList;
        this.subscribeData();
    }
}



