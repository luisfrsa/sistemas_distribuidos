import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public interface IntermediateServiceInterface extends java.rmi.Remote {

    public void receivePublishFromPublisher(List<String> publishedList) throws RemoteException;
    public void receiveSubscribeFromSubscriber(SubscriberServiceInterface subscriberServiceInterface,List<String> publishedList) throws RemoteException;
    public void receiveInterestFromNeightborIntermediate(IntermediateServiceInterface intermediateServiceInterface, List<String> listIntereset) throws RemoteException;
    public void receivePublishFromIntermediate(String published) throws RemoteException;
    public void sentToEveryOneWithInterest(List<String> publishList) throws RemoteException;
    public void sentInterestFromThisToNeighbor(List<String> publishedList) throws RemoteException;

    public void sendToSubscriber(String published) throws RemoteException;
    public IntermediateServiceInterface connectToIntermediate(String name) throws RemoteException;
    public SubscriberServiceInterface connectToSubscriber(String name) throws RemoteException;

    public String getName()  throws RemoteException;
    public void setName(String name)  throws RemoteException;
    public Map<IntermediateServiceInterface, List<String>> getIntermediateServiceListMap() throws RemoteException;
    public void setIntermediateServiceListMap(Map<IntermediateServiceInterface, List<String>> intermediateServiceListMap) throws RemoteException;
    public Map<SubscriberServiceInterface, List<String>> getSubscriberServiceListMap() throws RemoteException;
    public void setSubscriberServiceListMap(Map<SubscriberServiceInterface, List<String>> subscriberServiceListMap) throws RemoteException;

}
