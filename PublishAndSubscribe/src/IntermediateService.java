import java.rmi.RemoteException;

public class IntermediateService extends java.rmi.server.UnicastRemoteObject implements IntermediateServiceInterface {

    protected IntermediateService() throws RemoteException {
        super();
    }

}
