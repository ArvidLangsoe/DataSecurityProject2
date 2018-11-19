package permissions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Role {
    private String roleName;
    private Set<Permissions> permissions;
    private List<Role> inheretedRoles;


    public Role(String roleName){
        permissions=new HashSet<>();
        inheretedRoles=new LinkedList<>();
        this.roleName=roleName;
    }

    public String getRoleName() {
        return roleName;
    }


    public Set<Permissions> getPermissions(){
        return getPermissions(new HashSet<>());
    }
    private Set<Permissions> getPermissions(Set<String> roleNames) {
        Set<Permissions> currentPerm = new HashSet<>();
        for(Role r: inheretedRoles){
            if(!roleNames.contains(r.getRoleName())) {
                roleNames.add(r.getRoleName());
                currentPerm.addAll(r.getPermissions(roleNames));

            }
        }
        currentPerm.addAll(permissions);
        return currentPerm;
    }

    public boolean hasPermission(Permissions permission){
        return getPermissions().contains(permission);
    }


    public void addPermission(String permission){
        permissions.add(Permissions.valueOf(permission.toUpperCase()));
    }

    public void addInheretedRoles(Role inheretedRole) {
        this.inheretedRoles.add(inheretedRole);
    }

}
