import java.rmi.RemoteException;
import java.util.List;

public interface SubscriberServiceInterface extends java.rmi.Remote {

    public void receivePublishedMessage(String published) throws RemoteException;

    public List<IntermediateServiceInterface> createIntermediateList(String name) throws RemoteException;

    public void subscribeData() throws RemoteException;

    public void setName(String nome) throws RemoteException;

    public String getName() throws RemoteException;

    public List<IntermediateServiceInterface> getIntermediateServiceList() throws RemoteException;

    public void setIntermediateServiceList(List<IntermediateServiceInterface> intermediateServiceList) throws RemoteException;

    public List<String> getSubscribeList() throws RemoteException;

    public void setSubscribeList(List<String> subscribeList) throws RemoteException;
}

