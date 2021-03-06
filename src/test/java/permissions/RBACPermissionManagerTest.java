package permissions;

import org.junit.jupiter.api.Test;
import permissions.accesscontrol.rbac.RBACPermissionManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RBACPermissionManagerTest {

    @Test
    public void testRoleManager() throws IOException {
        RBACPermissionManager rm = new RBACPermissionManager();
        rm.importRolesFromFile("permissions/RolesTest.txt");
        List<Permissions> georgeActual = rm.getPermissionsOfUser("George");
        List<Permissions> aliceActual = rm.getPermissionsOfUser("Alice");

        List<Permissions> georgeExpected = Arrays.stream(new Permissions[]{Permissions.PRINT, Permissions.QUEUE}).collect(Collectors.toList());
        List<Permissions> aliceExpected = Arrays.stream(
                new Permissions[]{
                        Permissions.PRINT,
                        Permissions.QUEUE,
                        Permissions.START,
                        Permissions.STOP,
                        Permissions.RESTART,
                        Permissions.STATUS,
                        Permissions.READCONFIG,
                        Permissions.SETCONFIG,
                        Permissions.TOPQUEUE})
                .collect(Collectors.toList());

        assertTrue(georgeActual.containsAll(georgeExpected));
        assertTrue(aliceActual.containsAll(aliceExpected));

    }
}
