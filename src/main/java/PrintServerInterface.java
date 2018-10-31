import printer.Job;

import java.util.List;

public interface PrintServerInterface {

    public void print(String token, String filename, String printer);
    public List<Job> queue(String token);
    public void topQueue(String token, int job);
    public void start(String token);
    public void stop(String token);
    public void restart(String token);
    public String status(String token);
    public void readConfig(String token, String parameter);
    public void setConfig(String token, String parameter, String value);
    public void login(String username, String password);
    public void logout(String token);

}
