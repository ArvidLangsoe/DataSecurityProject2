package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

import java.util.Scanner;

public class PrintCommand extends Command {
    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {
        System.out.println("Please specify file and printer:");

        Scanner inputScan = new Scanner(System.in);

        String inputFileName = inputScan.nextLine();
        String inputPrinterName = inputScan.nextLine();

        System.out.println("Send file: " + inputFileName + " for printer: " + inputPrinterName);
        printServerInterface.print(token, inputFileName, inputPrinterName);

        return token;
    }

}
