package permissions;

import files.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrinterPermissionManager implements PermissionManager {

    HashMap<String, ArrayList<Permissions>> permissions;

    private final String FILEPATH = "Permissions.json";

    public PrinterPermissionManager(){
        permissions = JsonConverter.retrieveFromFile(FILEPATH);
        System.out.println(permissions);
    }


    @Override
    public List<Permissions> getPermissionsOfUser(String userName) {
        return permissions.get(userName);
    }

    @Override
    public boolean userHasPermission(String userName, Permissions permission) {
        boolean hasPermission = permissions.get(userName).contains(permission.toString());
        System.out.println("["+userName+"] Checking permission: "+permission+" -> "+hasPermission);
        return hasPermission;

    }


}
