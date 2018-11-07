package rmi;

import printer.IPrinter;
import printer.Job;
import printer.Printer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServant extends UnicastRemoteObject implements PrintServerInterface {

    IPrinter printer;

    RMIServant() throws RemoteException {
        super();
        printer=new Printer();
    }


    @Override
    public void print(String token, String filename, String printer) {
        //Check token
        this.printer.print(filename,printer);
    }

    @Override
    public List<Job> queue(String token) {
        return printer.queue();
    }

    @Override
    public void topQueue(String token, int job) {
        printer.topQueue(job);
    }

    @Override
    public void start(String token) {
        printer.start();
    }

    @Override
    public void stop(String token) {
        printer.stop();
    }

    @Override
    public void restart(String token) {
        printer.restart();
    }

    @Override
    public String status(String token) {

        return printer.status();
    }

    @Override
    public String readConfig(String token, String parameter) {
        return printer.readConfig(parameter);
    }

    @Override
    public void setConfig(String token, String parameter, String value) {
        printer.setConfig(parameter,value);
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public void logout(String token) {

    }

}
