import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class Main {

    IntermediateServiceInterface i1 = null;
    IntermediateServiceInterface i2 = null;
    IntermediateServiceInterface i3 = null;

    SubscriberServiceInterface a1 = null;
    SubscriberServiceInterface a2 = null;
    SubscriberServiceInterface a3 = null;

    public static void main(String[] args) {
        System.out.println("Starting Main");
        Main main = new Main();
        try {
            System.out.println("Running main...");
            main.run();
        } catch (RemoteException e) {
            System.out.println("Error RemoteException" + e.getMessage());
            doneAndWaitEnter();
        } catch (NotBoundException e) {
            System.out.println("Error NotBoundException" + e.getMessage());
            doneAndWaitEnter();
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Error MalformedURLException " + e.getMessage());
            doneAndWaitEnter();
            e.printStackTrace();
        }

    }

    public void run() throws RemoteException, NotBoundException, MalformedURLException {
        System.out.println("setIntermediate()");
        setIntermediate();

        System.out.println("setSubscribers()");
        setSubscribers();

        System.out.println("setIntermediateNeighbor()");

        setIntermediateNeighbor();

        System.out.println("setIntermediatesPublishers()");
        setIntermediatesPublishers();

        System.out.println("setPublishersIntermediates()");
        setPublishersIntermediates();

        System.out.println("setSubscriberInterest()");
        setSubscriberInterest();

        System.out.println("publishFromPublisher()");

        System.out.println("doneAndWaitEnter()");
        doneAndWaitEnter();
    }



    public void setIntermediate() throws RemoteException, NotBoundException, MalformedURLException {
        i1 = (IntermediateServiceInterface) Naming.lookup("//127.0.0.1:1099/i1");
        i2 = (IntermediateServiceInterface) Naming.lookup("//127.0.0.1:1099/i2");
        i3 = (IntermediateServiceInterface) Naming.lookup("//127.0.0.1:1099/i3");

        i1.setName("i1");
        i2.setName("i2");
        i3.setName("i3");
    }

    public void setSubscribers() throws RemoteException, NotBoundException, MalformedURLException {
        a1 = (SubscriberServiceInterface) Naming.lookup("//127.0.0.1:1099/a1");
        a2 = (SubscriberServiceInterface) Naming.lookup("//127.0.0.1:1099/a2");
        a3 = (SubscriberServiceInterface) Naming.lookup("//127.0.0.1:1099/a3");

        a1.setName("a1");
        a2.setName("a2");
        a3.setName("a3");

    }

    public void setIntermediateNeighbor() throws RemoteException {
        Map a1IntermediateServiceList = new HashMap<>();
        Map a2IntermediateServiceList = new HashMap<>();
        Map a3IntermediateServiceList = new HashMap<>();

        a1IntermediateServiceList.put(i2, new ArrayList<>());

        a2IntermediateServiceList.put(i1, new ArrayList<>());
        a2IntermediateServiceList.put(i3, new ArrayList<>());

        a3IntermediateServiceList.put(i2, new ArrayList<>());

        i1.setIntermediateServiceListMap(a1IntermediateServiceList);
        i2.setIntermediateServiceListMap(a2IntermediateServiceList);
        i3.setIntermediateServiceListMap(a3IntermediateServiceList);
    }

    public void setIntermediatesPublishers() throws RemoteException {
        Map<SubscriberServiceInterface, List<String>> i2subscriberServiceInterfaceMap = new HashMap<>();
        Map<SubscriberServiceInterface, List<String>> i3subscriberServiceInterfaceMap = new HashMap<>();

        i2subscriberServiceInterfaceMap.put(a1, new ArrayList<>());
        i2subscriberServiceInterfaceMap.put(a2, new ArrayList<>());
        i3subscriberServiceInterfaceMap.put(a3, new ArrayList<>());

        i2.setSubscriberServiceListMap(i2subscriberServiceInterfaceMap);
        i3.setSubscriberServiceListMap(i3subscriberServiceInterfaceMap);
    }

    public void setPublishersIntermediates() throws RemoteException {
        List<IntermediateServiceInterface> a1IntermediateServiceListList = new ArrayList<>();
        List<IntermediateServiceInterface> a2IntermediateServiceListList = new ArrayList<>();
        List<IntermediateServiceInterface> a3IntermediateServiceListList = new ArrayList<>();

        a1IntermediateServiceListList.add(i2);
        a2IntermediateServiceListList.add(i2);
        a3IntermediateServiceListList.add(i3);

        a1.setIntermediateServiceList(a1IntermediateServiceListList);
        a2.setIntermediateServiceList(a2IntermediateServiceListList);
        a3.setIntermediateServiceList(a3IntermediateServiceListList);

    }

    private void setSubscriberInterest() throws RemoteException {
        a1.setSubscribeList(Arrays.asList("a1"));
        a2.setSubscribeList(Arrays.asList("a2"));
        a3.setSubscribeList(Arrays.asList("a1", "a3"));
    }

    private static void doneAndWaitEnter() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
