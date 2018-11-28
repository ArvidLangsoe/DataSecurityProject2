package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

public class LogOutCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {

        printServerInterface.logout(token);

        System.out.println("User logged out.");
        return token;
    }
}
