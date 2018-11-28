package permissions.accesscontrol.acl;

import files.JsonConverter;
import permissions.PermissionManager;
import permissions.Permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ACLPermissionManager implements PermissionManager {

    HashMap<String, ArrayList<Permissions>> permissions;

    private JsonConverter jsonConverter = new JsonConverter();

    private final String FILEPATH = "permissions/Permissions.json";

    public ACLPermissionManager(){
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
