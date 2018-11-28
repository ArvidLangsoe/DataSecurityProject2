package login;

import org.mindrot.jbcrypt.BCrypt;
import permissions.PermissionManager;
import permissions.Permissions;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class LoginController {
    HashMap<String, String> passwords;
    HashMap<String, Token> tokens = new HashMap<>();
    private PermissionManager permissionManager;

    public LoginController(PermissionManager permissionManager){
        this.permissionManager = permissionManager;
        passwords = new HashMap<String, String>();

        try {
            passwords = (HashMap<String, String>) retrieveData();

        } catch (IOException e) {
            initData();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    private void initData(){
        passwords.put("User", BCrypt.hashpw("Password", BCrypt.gensalt()));
        passwords.put("Arvid", BCrypt.hashpw("12", BCrypt.gensalt()));
        passwords.put("Peter", BCrypt.hashpw("23", BCrypt.gensalt()));
        passwords.put("Mads", BCrypt.hashpw("45", BCrypt.gensalt()));

        passwords.put("David", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("Erica", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("Fred", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("George", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("Cecilia", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("Bob", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("Alice", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("Henry", BCrypt.hashpw("Pass", BCrypt.gensalt()));
        passwords.put("Ida", BCrypt.hashpw("Pass", BCrypt.gensalt()));

        try {
            writeData(passwords);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Token login(String username, String password) throws Exception {
        if (passwords.get(username) != null) {
            if (BCrypt.checkpw(password, passwords.get(username))) {
                return generateToken(username);
            } else {
                throw new Exception("INVALID PASSWORD");
            }
        }else{
            throw new Exception("INVALID USERNAME");
        }
    }

    public void logout(Token token){
        tokens.remove(token.getUsername());
    }

    public boolean isCorrectToken(Token token) throws Exception {
        if(tokens.get(token.getUsername()) == null)
            throw new Exception("No token given, please login again.");

        return tokens.get(token.getUsername()).equals(token);
    }

    private Token generateToken(String username){
        String finalToken = null;

        SecureRandom random = new SecureRandom();

        byte bytes[] = new byte[64];
        random.nextBytes(bytes);

        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        finalToken = encoder.encodeToString(bytes);

            // The basic encoder encodes the input with a number of bytes and maps the output to a list of
            // characters in A-Za-z0-9+/ character set.

        System.out.println("["+username+"]"+" Generated token : " + finalToken);

        Token returnToken =  new Token(finalToken, username);
        tokens.put(username, returnToken);

        return returnToken;
    }

    private void writeData(Map<String, String> map) throws IOException {
        FileOutputStream fos = new FileOutputStream("users.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.close();
    }

    private Map<String, String> retrieveData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("users.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Map result = (Map) ois.readObject();
        ois.close();
        return result;
    }

    public boolean hasPermission(Token token, Permissions permission) {
        if(permissionManager==null){
            return true;
        }
        return permissionManager.userHasPermission(token.getUsername(),permission);

    }

    public List<Permissions> getPermissions(Token token) {
        if(permissionManager==null){
            return Arrays.stream(Permissions.values()).collect(Collectors.toList());
        }
        return permissionManager.getPermissionsOfUser(token.getUsername());
    }
}
