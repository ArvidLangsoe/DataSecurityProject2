package rmi.commands;

import login.Token;
import rmi.PrintServerInterface;

import java.util.Scanner;

public class LoginCommand extends Command {

    @Override
    public Token run(Token token, PrintServerInterface printServerInterface) throws Exception {
        System.out.println("Please enter user credentials:");

        Scanner inputScan = new Scanner(System.in);

        String inputUserName = inputScan.nextLine();
        String inputUserPass = inputScan.nextLine();

        token = printServerInterface.login(inputUserName, inputUserPass);

        if (token.equals("INVALID PASSWORD")) {
            System.out.println("Password not recognised.");
        } else if (token.equals("INVALID USERNAME")) {
            System.out.println("Username not recognised.");
        } else {
            System.out.println("Logged in successfully.\nConstructing User Interface...");
        }

        return token;
    }
}

