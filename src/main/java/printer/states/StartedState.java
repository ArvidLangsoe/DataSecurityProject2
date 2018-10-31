package printer.states;

import printer.Job;
import printer.Printer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StartedState extends State{
    Queue<Job> printQueue;
    int jobNum= 0;

    public StartedState(Printer printer) {
        super(printer);
        printQueue = new LinkedList<Job>();
    }


    public void print(String filename, String printer) {
        System.out.println("Printing file: "+filename);
        printQueue.add(new Job(jobNum++,filename));
    }

    public List<Job> queue() {
        return null;
    }

    public void topQueue(int job) {

    }

    public void start() {

    }

    public void stop() {
        System.out.println("Printer stopping");
        printer.setCurrentState(new StoppedState(printer));
    }

    public void restart() {

    }

    public String status() {
        return null;
    }

    public void readConfig(String parameter) {

    }

    public void setConfig(String parameter, String value) {

    }
}
