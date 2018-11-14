package rmi;

import login.Token;
import printer.Job;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PrintServerInterface extends Remote {

    void print(Token token, String filename, String printer) throws Exception;

    List<Job> queue(Token token) throws Exception;

    void topQueue(Token token, int job) throws Exception;

    void start(Token token) throws Exception;

    void stop(Token token) throws Exception;

    void restart(Token token) throws Exception;

    String status(Token token) throws Exception;

    String readConfig(Token token, String parameter) throws Exception;

    void setConfig(Token token, String parameter, String value) throws Exception;

    Token login(String username, String password) throws Exception;

    void logout(Token token) throws RemoteException;

}
