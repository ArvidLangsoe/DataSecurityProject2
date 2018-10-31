package printer;

import java.util.List;

public interface IPrinter {

    void print(String filename, String printer);
    List queue();
    void topQueue(int job);
    void start();
    void stop();
    void restart();
    String status();
    String readConfig(String parameter);
    void setConfig(String parameter, String value);
}
