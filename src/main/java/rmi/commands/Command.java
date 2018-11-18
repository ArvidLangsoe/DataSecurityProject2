package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

abstract class Command {

    public abstract Token run(Token token, PrintServerInterface printServerInterface) throws Exception;

}


