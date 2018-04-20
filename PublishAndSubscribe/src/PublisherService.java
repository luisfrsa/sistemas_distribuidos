import java.rmi.RemoteException;

public class PublisherService extends java.rmi.server.UnicastRemoteObject implements PublisherServiceInterface {

    protected PublisherService() throws RemoteException {
        super();
    }

}
