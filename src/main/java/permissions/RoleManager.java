package permissions;

import files.FileLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RoleManager {

    HashMap<String, Role> userRoles;
    HashMap<String, Role> availableRoles;


    public RoleManager(){
        userRoles = new HashMap<>();
        availableRoles =new HashMap<>();
    }

    public List<Permissions> getPermissionOfUser(String userName){
        return userRoles.get(userName).getPermissions().stream().collect(Collectors.toList());
    }

    public void importRoles() throws IOException {
        importRolesFromFile("permissions/Roles.txt");
    }

    public void importRolesFromFile(String filePath) throws IOException {
        FileLoader fl = new FileLoader();
        String[] lines = fl.loadLines(filePath);
        for (String line: lines) {
            importLine(line);
        }
    }

    private void importLine(String line) {
        String lineNoWhiteSpace =line.replaceAll(" ","");
        if(line.isEmpty()){
            return;
        }
        String[] lineParts=lineNoWhiteSpace.split("=");
        String identifier = lineParts[0];
        String[] content = lineParts[1].split(",");
        if(identifier.charAt(0)=='#'){
            addUser(identifier.substring(1),content);
        }else if(identifier.charAt(0)=='$'){
            addRoleAndPermission(identifier.substring(1),content);
        }else{
            System.err.println("Line could not be imported: \""+line+"\"");
        }
    }

    private void addRoleAndPermission(String roleName, String[] content) {
        addRole(roleName);
        Role currentRole = availableRoles.get(roleName);
        for (String c: content) {
            if(c.charAt(0)=='$'){
                String inRoleName=c.substring(1);
                addRole(inRoleName);
                Role newRole = availableRoles.get(inRoleName);
                currentRole.addInheretedRoles(newRole);
            }
            else{
                try{
                    currentRole.addPermission(c);
                }catch (IllegalArgumentException e){
                    System.err.println("Permission does not exist and was skipped.");
                    System.err.println("Role Name: "+ roleName + " Permision: "+c);
                }
            }
        }
    }

    private void addUser(String name, String[] content) {
        if(content.length>1){
            System.err.println("Users can only have one role, skipping user: "+name);
            return;
        }
        if(userRoles.containsKey(name)){
            System.err.println("User defined multiple times, applying first instace of: "+name);
        }
        String roleName = content[0].substring(1);
        addRole(roleName);
        userRoles.put(name,availableRoles.get(roleName));
    }

    private void addRole(String roleName) {
        if (!availableRoles.containsKey(roleName)) {
            Role r = new Role(roleName);
            availableRoles.put(r.getRoleName(), r);
        }
    }
}
