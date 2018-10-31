package printer.states;

import printer.Job;
import printer.Printer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class StartedState extends State{
    Queue<Job> printQueue;
    Map<String,String> config;
    int jobNum= 0;

    public StartedState(Printer printer) {
        super(printer);
        printQueue = new LinkedList<Job>();
        config = new ConcurrentHashMap<String, String>();
    }

    public void print(String filename, String printer) {
        System.out.println("Printing file: "+filename);
        printQueue.add(new Job(jobNum++,filename));
    }

    public List<Job> queue() {
        return (List)printQueue;
    }

    public void topQueue(int job) {
        throw new NotImplementedException();
    }

    public void start() {
        System.out.println("Printer has already started");
    }

    public void stop() {
        System.out.println("Printer stopping");
        printer.setCurrentState(new StoppedState(printer));
    }

    public void restart() {
        System.out.println("Restarting printer");
        printer.setCurrentState(new StartedState(printer));
    }

    public String status() {
        if(printQueue.isEmpty()) {
            return "Printer is started";
        }
        else{
            return "Printer is printing";
        }
    }

    public String readConfig(String parameter) {
        return config.get(parameter);
    }

    public void setConfig(String parameter, String value) {
        config.put(parameter,value);
    }
}
