package permissions;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleManagerTest {

    @Test
    public void testRoleManager() throws IOException {
        RoleManager rm = new RoleManager();
        rm.importRolesFromFile("permissions/RolesTest.txt");
        List<Permissions> georgeActual = rm.getPermissionOfUser("George");
        List<Permissions> aliceActual = rm.getPermissionOfUser("Alice");

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
