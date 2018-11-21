package permissions;

import files.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrinterPermissionManager implements PermissionManager {

    HashMap<String, ArrayList<Permissions>> permissions;

    private JsonConverter jsonConverter = new JsonConverter();

    private final String FILEPATH = "Permissions.json";

    public PrinterPermissionManager(){
        permissions = jsonConverter.retrieveFromFile(FILEPATH);
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
