package permissions;

import org.junit.jupiter.api.Test;
import permissions.accesscontrol.rbac.Role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleTest {

    @Test
    public void testRole(){
        Role r1 = new Role("r1");
        Role r2 = new Role("r2");

        r2.addInheretedRoles(r1);
        r1.addPermission("readConfig");
        r1.addPermission("status");

        r2.addPermission("print");
        r2.addPermission("restart");
        r2.addPermission("status");

        assertTrue(r1.hasPermission(Permissions.READCONFIG));
        assertTrue(r1.hasPermission(Permissions.STATUS));
        assertFalse(r1.hasPermission(Permissions.PRINT));
        assertFalse(r1.hasPermission(Permissions.RESTART));

        assertTrue(r2.hasPermission(Permissions.PRINT));
        assertTrue(r2.hasPermission(Permissions.RESTART));
        assertTrue(r2.hasPermission(Permissions.STATUS));
        assertTrue(r2.hasPermission(Permissions.READCONFIG));
    }

    @Test
    public void testForCicularInheretance(){
        Role r1 = new Role("r1");
        Role r2 = new Role("r2");

        r2.addInheretedRoles(r1);
        r1.addInheretedRoles(r2);
        r1.getPermissions();

    }

}
