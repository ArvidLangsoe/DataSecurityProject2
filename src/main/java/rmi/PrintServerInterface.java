package rmi;

import printer.Job;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PrintServerInterface extends Remote {

    void print(String token, String filename, String printer) throws RemoteException;

    List<Job> queue(String token) throws RemoteException;

    void topQueue(String token, int job) throws RemoteException;

    void start(String token) throws RemoteException;

    void stop(String token) throws RemoteException;

    void restart(String token) throws RemoteException;

    String status(String token) throws RemoteException;

    String readConfig(String token, String parameter) throws RemoteException;

    void setConfig(String token, String parameter, String value) throws RemoteException;

    void login(String username, String password) throws RemoteException;

    void logout(String token) throws RemoteException;

}
