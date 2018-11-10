package login;

import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.security.SecureRandom;
import java.util.*;

public class LoginController {

    List<String> tokens = new ArrayList<String>();
    HashMap<String, String> passwords;

    public LoginController(){
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

        try {
            writeData(passwords);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String login(String username, String password) {
        if (passwords.get(username) != null) {
            if (BCrypt.checkpw(password, passwords.get(username))) {
                return generateToken();
            } else {
                return "INVALID PASSWORD";
            }
        }else{
            return "INVALID USERNAME";
        }
    }

    public void logout(String token){
        tokens.remove(token);
    }

    public boolean isCorrectToken(String token){
        return tokens.contains(token);
    }

    private String generateToken(){
        String finalToken = null;

        while(finalToken == null || tokens.contains(finalToken)) {
            SecureRandom random = new SecureRandom();

            byte bytes[] = new byte[64];
            random.nextBytes(bytes);

            Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
            String token = encoder.encodeToString(bytes);

            // The basic encoder encodes the input with a number of bytes and maps the output to a list of
            // characters in A-Za-z0-9+/ character set.

            System.out.println("Generated token : " + token);

            return token;
        }

        tokens.add(finalToken);
        return finalToken;
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
}
