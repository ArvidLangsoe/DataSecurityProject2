import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(5099);
            registry.rebind("rmiserver", new RMIServant());
            System.out.println("Server started, listening for client...");
        } catch (RemoteException e) {
            System.out.println("Failed to start server: " + e.getMessage());
        }

    }

}
