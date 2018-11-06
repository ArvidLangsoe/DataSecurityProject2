package rmi;

import printer.Job;
import printer.Printer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServant extends UnicastRemoteObject implements PrintServerInterface {

    Printer printer;

    RMIServant() throws RemoteException {
        super();
    }


    @Override
    public void print(String token, String filename, String printer) {
    }

    @Override
    public List<Job> queue(String token) {
        return null;
    }

    @Override
    public void topQueue(String token, int job) {

    }

    @Override
    public void start(String token) {
        System.out.println("Starting printer using token.");
    }

    @Override
    public void stop(String token) {

    }

    @Override
    public void restart(String token) {

    }

    @Override
    public String status(String token) {
        return null;
    }

    @Override
    public String readConfig(String token, String parameter) {
        return null;
    }

    @Override
    public void setConfig(String token, String parameter, String value) {

    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public void logout(String token) {

    }

}
