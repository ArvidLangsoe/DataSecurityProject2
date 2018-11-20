package permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrinterPermissionManager implements PermissionManager {

    HashMap<String, ArrayList<Permissions>> permissions = new HashMap<>();

    @Override
    public List<Permissions> getPermissionsOfUser(String userName) {
        return null;
    }

    @Override
    public boolean userHasPermission(String userName, Permissions permission) {
        return false;
    }


}
