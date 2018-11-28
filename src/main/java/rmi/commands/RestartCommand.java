package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

public class RestartCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {

        printServerInterface.restart(token);

        System.out.println("Printer Reset.");
        return token;
    }
}
