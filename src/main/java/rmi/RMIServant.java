package rmi;

import login.LoginController;
import printer.IPrinter;
import printer.Job;
import printer.Printer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServant extends UnicastRemoteObject implements PrintServerInterface {

    IPrinter printer;

    LoginController loginController;

    RMIServant() throws RemoteException {
        super();
        printer=new Printer();
        loginController = new LoginController();
    }


    private void checkToken(String token) throws Exception {
        if(!loginController.isCorrectToken(token))
            throw new Exception("Invalid token, please login again.");

    }

    @Override
    public void print(String token, String filename, String printer) throws Exception {
        checkToken(token);
        this.printer.print(filename,printer);
    }

    @Override
    public List<Job> queue(String token) throws Exception {
        checkToken(token);
        return printer.queue();
    }

    @Override
    public void topQueue(String token, int job) throws Exception {
        checkToken(token);
        printer.topQueue(job);
    }

    @Override
    public void start(String token) throws Exception {
        checkToken(token);
        printer.start();
    }

    @Override
    public void stop(String token) throws Exception {
        checkToken(token);
        printer.stop();
    }

    @Override
    public void restart(String token) throws Exception {
        checkToken(token);
        printer.restart();
    }

    @Override
    public String status(String token) throws Exception {
        checkToken(token);
        return printer.status();
    }

    @Override
    public String readConfig(String token, String parameter) throws Exception {
        checkToken(token);
        return printer.readConfig(parameter);
    }

    @Override
    public void setConfig(String token, String parameter, String value) throws Exception {
        checkToken(token);
        printer.setConfig(parameter,value);
    }

    @Override
    public String login(String username, String password) {
        return loginController.login(username, password);
    }

    @Override
    public void logout(String token) {
        loginController.logout(token);
    }

}
