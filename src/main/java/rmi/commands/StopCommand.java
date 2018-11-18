package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

public class StopCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {

        printServerInterface.stop(token);

        System.out.println("Printer Stopped.");
        return token;
    }
}
