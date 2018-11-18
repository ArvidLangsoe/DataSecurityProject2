package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

public class StartCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {

        printServerInterface.start(token);

        System.out.println("Printer Started.");
        return token;
    }
}
