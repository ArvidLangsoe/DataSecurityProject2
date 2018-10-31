package printer;

import java.util.List;

public interface IPrinter {

    public void print(String filename, String printer);
    public List<Job> queue();
    public void topQueue(int job);
    public void start();
    public void stop();
    public void restart();
    public String status();
    public void readConfig(String parameter);
    public void setConfig(String parameter, String value);
}
