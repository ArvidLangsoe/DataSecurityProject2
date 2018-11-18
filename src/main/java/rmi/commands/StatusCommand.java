package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

public class StatusCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {

        System.out.println("Printer status: " + printServerInterface.status(token));

        return token;
    }
}
