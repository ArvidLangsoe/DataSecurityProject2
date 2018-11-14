package login;

import java.io.Serializable;

public class Token implements Serializable {



    private String token = null;
    private String username = null;

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Token(String token, String username) {
        this.token = token;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


    public boolean equals(Token token){
        return this.token.equals(token.getToken()) && this.getUsername().equals(token.getUsername());
    }
}
