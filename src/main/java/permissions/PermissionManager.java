package permissions;

import java.util.List;

public interface PermissionManager {

    List<Permissions> getPermissionsOfUser(String userName);
    boolean userHasPermission(String userName, Permissions permission);
}
