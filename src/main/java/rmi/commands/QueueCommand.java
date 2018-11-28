package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

public class QueueCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {
        System.out.println(printServerInterface.queue(token));
        return token;
    }
}
