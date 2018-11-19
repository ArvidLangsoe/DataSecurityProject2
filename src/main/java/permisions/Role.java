package permisions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Role {
    private String roleName;
    private Set<String> permissions;
    private List<Role> inheretedRoles;


    public Role(String roleName){
        permissions=new HashSet<>();
        inheretedRoles=new LinkedList<>();
        this.roleName=roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void addPermission(String permission){
        permissions.add(permission);
    }

    public void addInheretedRoles(Role inheretedRole) {
        this.inheretedRoles.add(inheretedRole);
    }
}
