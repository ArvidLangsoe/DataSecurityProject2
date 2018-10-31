package printer.states;

import printer.IPrinter;
import printer.Job;
import printer.Printer;

import java.util.List;

public class StoppedState extends State {

    public StoppedState(Printer printer) {
        super(printer);
    }
    public void print(String filename, String printer) {
        throw new IllegalStateException("Printer is turned off");
    }

    public List<Job> queue() {
        throw new IllegalStateException("Printer is turned off");
    }

    public void topQueue(int job) {
        throw new IllegalStateException("Printer is turned off");
    }

    public void start() {
        System.out.println("Printer starting");
        printer.setCurrentState(new StartedState(printer));
    }

    public void stop() {
        System.out.println("Printer was has already stopped");
    }

    public void restart() {
        this.start();
    }

    public String status() {
        return "Printer is stopped.";
    }

    public void readConfig(String parameter) {
        throw new IllegalStateException("Printer is turned off");
    }

    public void setConfig(String parameter, String value) {
        throw new IllegalStateException("Printer is turned off");
    }
}
