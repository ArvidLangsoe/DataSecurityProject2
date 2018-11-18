package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

import java.util.Scanner;

public class ShowConfigCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {

        System.out.println("Please specify config to read.");

        Scanner inputScan = new Scanner(System.in);

        String inputConfigName = inputScan.nextLine();

        System.out.println(inputConfigName + ": " + printServerInterface.readConfig(token, inputConfigName));

        return token;
    }
}
