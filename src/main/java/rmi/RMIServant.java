package rmi;

import login.LoginController;
import login.Token;
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


    private void checkToken(Token token) throws Exception {
        if(token == null)
            throw new Exception("No token given, please login again.");

        if(!loginController.isCorrectToken(token))
            throw new Exception("Invalid token, please login again.");

    }

    @Override
    public void print(Token token, String filename, String printer) throws Exception {
        checkToken(token);
        this.printer.print(filename,printer);
    }

    @Override
    public List<Job> queue(Token token) throws Exception {
        checkToken(token);
        return printer.queue();
    }

    @Override
    public void topQueue(Token token, int job) throws Exception {
        checkToken(token);
        printer.topQueue(job);
    }

    @Override
    public void start(Token token) throws Exception {
        checkToken(token);
        printer.start();
    }

    @Override
    public void stop(Token token) throws Exception {
        checkToken(token);
        printer.stop();
    }

    @Override
    public void restart(Token token) throws Exception {
        checkToken(token);
        printer.restart();
    }

    @Override
    public String status(Token token) throws Exception {
        checkToken(token);
        return printer.status();
    }

    @Override
    public String readConfig(Token token, String parameter) throws Exception {
        checkToken(token);
        return printer.readConfig(parameter);
    }

    @Override
    public void setConfig(Token token, String parameter, String value) throws Exception {
        checkToken(token);
        printer.setConfig(parameter,value);
    }

    @Override
    public Token login(String username, String password) throws Exception {
        return loginController.login(username, password);
    }

    @Override
    public void logout(Token token) {
        loginController.logout(token);
    }

}
