package permissions;

import files.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrinterPermissionManager implements PermissionManager {

    HashMap<String, ArrayList<Permissions>> permissions = new HashMap<>();

    private final String FILEPATH = "Permissions.json";


    @Override
    public List<Permissions> getPermissionsOfUser(String userName) {
        return permissions.get(userName);
    }

    @Override
    public boolean userHasPermission(String userName, Permissions permission) {
        return permissions.get(userName).contains(permission);
    }


}
