import printer.Job;

import java.util.List;

public interface PrintServerInterface {

    void print(String token, String filename, String printer);

    List<Job> queue(String token);

    void topQueue(String token, int job);

    void start(String token);

    void stop(String token);

    void restart(String token);

    String status(String token);

    String readConfig(String token, String parameter);

    void setConfig(String token, String parameter, String value);

    void login(String username, String password);

    void logout(String token);

}
