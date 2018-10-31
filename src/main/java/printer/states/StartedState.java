package printer.states;

import printer.Job;
import printer.Printer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StartedState extends State{
    Queue<Job> printQueue;
    Map<String,String> config;
    int jobNum= 1;

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
        List<Job> jobList = (List<Job>) printQueue;
        List<Job> jobsToMove= jobList.stream()
                .filter(i->i.jobNumber==job)
                .collect(Collectors.toList());
        List<Job> filteredJobs= jobList.stream()
                .filter(i->i.jobNumber!=job)
                .collect(Collectors.toCollection(()->new LinkedList<Job>()));

        filteredJobs.addAll(0,jobsToMove);
        printQueue=(Queue)filteredJobs;


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
            return "Started";
        }
        else{
            return "Printing";
        }
    }

    public String readConfig(String parameter) {
        return config.get(parameter);
    }

    public void setConfig(String parameter, String value) {
        config.put(parameter,value);
    }
}
