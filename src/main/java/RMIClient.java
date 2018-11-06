import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class RMIClient {


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        PrintServerInterface service = (PrintServerInterface) Naming.lookup("rmi://localhost:5099/rmiserver");


    }
}
