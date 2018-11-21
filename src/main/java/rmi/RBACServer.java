package rmi;

import permissions.accesscontrol.rbac.RBACPermissionManager;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RBACServer {

    public static void main(String[] args) throws IOException {
        RBACPermissionManager rbacPermissionManager = new RBACPermissionManager();

        rbacPermissionManager.importRoles();


        try {
            Registry registry = LocateRegistry.createRegistry(5099);
            registry.rebind("rmiserver", new RMIServant(rbacPermissionManager));
            System.out.println("Server started, listening for client...");
        } catch (RemoteException e) {
            System.out.println("Failed to start server: " + e.getMessage());
        }

    }

}
