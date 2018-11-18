package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

import java.util.Scanner;

public class EditConfigCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {

        System.out.println("Please specify config to read and new value.");

        Scanner inputScan = new Scanner(System.in);

        String inputEditConfigName = inputScan.nextLine();
        String inputEditConfigNewText = inputScan.nextLine();

        printServerInterface.setConfig(token, inputEditConfigName, inputEditConfigNewText);

        System.out.println("Config set");

        return token;
    }
}
