package printer;

import printer.states.StartedState;

import java.awt.peer.PanelPeer;
import java.util.List;

public class Printer implements IPrinter{
    IPrinter currentState;

    public Printer(){
        currentState=new StartedState(this);
    }

    public void setCurrentState(IPrinter printer){
        currentState = printer;
    }

    public void print(String filename, String printer) {
        currentState.print(filename,printer);
    }

    public List<Job> queue() {
        return currentState.queue();
    }

    public void topQueue(int job) {
        currentState.topQueue(job);
    }

    public void start() {
        currentState.start();
    }

    public void stop() {
        currentState.stop();
    }

    public void restart() {
        currentState.restart();
    }

    public String status() {
        return currentState.status();
    }

    public String readConfig(String parameter) {
        return currentState.readConfig(parameter);
    }

    public void setConfig(String parameter, String value) {

    }
}
