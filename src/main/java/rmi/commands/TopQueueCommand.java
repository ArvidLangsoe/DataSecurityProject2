package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

import java.util.Scanner;

public class TopQueueCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {
        System.out.println("Please specify fileindex to push to top of queue.");

        Scanner inputScan = new Scanner(System.in);

        int inputJobIndex = Integer.parseInt(inputScan.nextLine());

        printServerInterface.topQueue(token, inputJobIndex);

        System.out.println("Pushed file index: " + inputJobIndex + " to top of queue.");
        return token;
    }
}
