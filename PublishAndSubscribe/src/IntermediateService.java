import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class IntermediateService extends java.rmi.server.UnicastRemoteObject implements IntermediateServiceInterface {

    public static final int DELAY = 100;
    private String name;
    private Map<IntermediateServiceInterface, List<String>> intermediateServiceListMap = new HashMap<>();
    private Map<SubscriberServiceInterface, List<String>> subscriberServiceListMap = new HashMap<>();
    private Set<String> sentString = new HashSet<>();
    private Set<String> receivedString = new HashSet<>();

    protected IntermediateService() throws RemoteException {
        super();
    }


    @Override
    public void receiveSubscribeFromSubscriber(SubscriberServiceInterface subscriberServiceInterface, List<String> publishedList) throws RemoteException {
        System.out.println("Recebendo assunatura de assinante  " + subscriberServiceInterface.getName());
        subscriberServiceListMap.replace(subscriberServiceInterface, publishedList);
        sentInterestFromThisToNeighbor(publishedList);
    }


    @Override
    public void sentInterestFromThisToNeighbor(List<String> publishedList) {
        System.out.print("Enviando interesse do Intermediario  " + getName() + " a seus Intermediarios vizinhos sobre  " + publishedList);
        intermediateServiceListMap.forEach((intermediate, listStrings) -> {
            try {
                System.out.print(" ,Vizinhos: " + intermediate.getName());
                intermediate.receiveInterestFromNeightborIntermediate(this, publishedList);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" ");
    }

    @Override
    public void receiveInterestFromNeightborIntermediate(IntermediateServiceInterface intermediateServiceInterface, List<String> listIntereset) throws RemoteException {
        String strPrint = "";
        List<String> interestList = intermediateServiceListMap.get(intermediateServiceInterface);
        List<String> newListOfIntereset = listIntereset.stream().filter(newIntereset -> !interestList.contains(newIntereset)).collect(Collectors.toList());

        if (newListOfIntereset.size() > 0) {
            strPrint += ("O Intermediario " + this.name + " esta recebendo  interesse do intermediario vizinho: " + intermediateServiceInterface.getName() + " sobre  " + listIntereset.toString());
            strPrint += ("Atualizando lista de interesses de " + interestList.toString());
            interestList.addAll(newListOfIntereset);
            strPrint += ("Atualizando lista de interesses para " + interestList.toString());
//            intermediateServiceListMap.replace(intermediateServiceInterface, interestList);
            intermediateServiceListMap.replace(intermediateServiceInterface, newListOfIntereset);
            sentInterestFromThisToNeighbor(newListOfIntereset);
        }
        System.out.println(strPrint.join("\n"));
    }

    @Override
    public void receivePublishFromPublisher(List<String> publishedList) throws RemoteException {
        System.out.println("Recebendo Publicacao  " + publishedList.toString());
        this.receivedString.addAll(publishedList);
        this.sentToEveryOneWithInterest(publishedList);
    }

    @Override
    public void sentToEveryOneWithInterest(List<String> publishList) throws RemoteException {
        System.out.print("Enviando, para vizinhos, informacao para interessados a  " + publishList.toString());
        publishList.forEach(string -> sendToSubscriber(string));
        intermediateServiceListMap.forEach((intermediate, listStrings) -> {
            List<String> matchedPublish = listStrings.stream().filter(string -> publishList.contains(string)).collect(Collectors.toList());
            if (matchedPublish.size() > 0) {
                matchedPublish.forEach(stringToSend -> {
                    try {
                        System.out.print(" para: " + intermediate.getName());
                        intermediate.receivePublishFromIntermediate(stringToSend);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        System.out.println(" ");
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void receivePublishFromIntermediate(String published) throws RemoteException {
        System.out.println("Recebendo publicacao de intermediario " + published);
        sendToSubscriber(published);
        if (!sentString.contains(published)) {
            sentString.add(published);
            intermediateServiceListMap.forEach((intermediate, listStrings) -> {
                if (listStrings.contains(published)) {

                    try {
                        System.out.println("E enviando para " + intermediate.getName());
                        intermediate.receivePublishFromIntermediate(published);
//                        intermediate.sentToEveryOneWithInterest(published);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void sendToSubscriber(String published) {
        System.out.println("Enviando para assinante " + published);

        subscriberServiceListMap.forEach((subscriber, listStrings) -> {
            Boolean interest = listStrings.contains(published);
            if (interest) {
                try {
                    System.out.println("Assinante encontrado " + subscriber.getName());
                    subscriber.receivePublishedMessage(published);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public SubscriberServiceInterface connectToSubscriber(String name) {
        System.out.println("Conectando ao assinante: " + name);
        SubscriberServiceInterface subscriberServiceConnection = null;
        try {
            subscriberServiceConnection = (SubscriberServiceInterface) Naming.lookup("//127.0.0.1:1099/ " + name);
            subscriberServiceListMap.put(subscriberServiceConnection, new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscriberServiceConnection;
    }

    @Override
    public IntermediateServiceInterface connectToIntermediate(String name) {
        System.out.println("Conectando ao intermediario: " + name);
        IntermediateServiceInterface intermediateServiceConnection = null;
        try {
            intermediateServiceConnection = (IntermediateServiceInterface) Naming.lookup("//127.0.0.1:1099/ " + name);
            intermediateServiceListMap.put(intermediateServiceConnection, new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intermediateServiceConnection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntermediateService)) return false;
        if (!super.equals(o)) return false;
        IntermediateService that = (IntermediateService) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("Name has been setted to  " + name);
        this.name = name;
    }

    public Map<IntermediateServiceInterface, List<String>> getIntermediateServiceListMap() {
        return intermediateServiceListMap;
    }

    public void setIntermediateServiceListMap(Map<IntermediateServiceInterface, List<String>> intermediateServiceListMap) {
        this.intermediateServiceListMap = intermediateServiceListMap;
    }

    public Map<SubscriberServiceInterface, List<String>> getSubscriberServiceListMap() {
        return subscriberServiceListMap;
    }

    public void setSubscriberServiceListMap(Map<SubscriberServiceInterface, List<String>> subscriberServiceListMap) {
        this.subscriberServiceListMap = subscriberServiceListMap;
    }
}
