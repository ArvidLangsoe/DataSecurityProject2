package rmi;

import printer.Job;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PrintServerInterface extends Remote {

    void print(String token, String filename, String printer) throws Exception;

    List<Job> queue(String token) throws Exception;

    void topQueue(String token, int job) throws Exception;

    void start(String token) throws Exception;

    void stop(String token) throws Exception;

    void restart(String token) throws Exception;

    String status(String token) throws Exception;

    String readConfig(String token, String parameter) throws Exception;

    void setConfig(String token, String parameter, String value) throws Exception;

    String login(String username, String password) throws RemoteException;

    void logout(String token) throws RemoteException;

}
