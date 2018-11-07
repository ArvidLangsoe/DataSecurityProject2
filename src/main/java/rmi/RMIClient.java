package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class RMIClient {


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        String token = "Hackerman";

        PrintServerInterface userSession = (PrintServerInterface) Naming.lookup("rmi://localhost:5099/rmiserver");

        printWelcomeMessage();

        Scanner inputScan = new Scanner(System.in);

        int correctInput;
        Scanner inputScanner = new Scanner(System.in);
        while (true){
            correctInput = inputScan.nextInt();
            switch (correctInput) {
                case 1:
                    System.out.println("Please specify file and printer:");
                    String inputFileName = inputScanner.next();
                    String inputPrinterName = inputScanner.next();
                    System.out.println("Sending file: " + inputFileName + " for printer: " + inputPrinterName);
                    userSession.print(token, inputFileName, inputPrinterName);
                    break;
                case 2:
                    userSession.queue(token);
                    break;
                case 3:
                    System.out.println("Please specify fileindex to push to top of queue.");
                    int inputJobIndex = inputScanner.nextInt();
                    userSession.topQueue(token, inputJobIndex);
                    System.out.println("Pushing file index: " + inputJobIndex + " to top of queue.");
                    break;
                case 4:
                    userSession.start(token);
                    break;
                case 5:
                    userSession.stop(token);
                    break;
                case 6:
                    userSession.restart(token);
                    break;
                case 7:
                    userSession.status(token);
                    break;
                case 8:
                    System.out.println("Please specify config to read.");
                    String inputConfigName = inputScanner.next();
                    userSession.readConfig(token, inputConfigName);
                    break;
                case 9:
                    System.out.println("Please specify config to read and new value.");
                    String inputEditConfigName = inputScanner.next();
                    String inputEditConfigNewText = inputScanner.next();
                    userSession.setConfig(token, inputEditConfigName, inputEditConfigNewText);
                    break;
                case 10:
                    System.out.println("Please specify username and password.");
                    String inputUserName = inputScanner.next();
                    String inputUserPass = inputScanner.next();
                    userSession.login(inputUserName, inputUserPass);
                    break;
                case 11:
                    System.out.println("Logging out of user.");
                    userSession.logout(token);
                    break;
                case 000:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Input not recognised. Please try again");
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("Welcome to the printer service. Please enter your desired command-number ('help' for list of commands'):\n\n" +
                "1: Print file name on the specified printer.\n" +
                "2: Lists the print queue.\n" +
                "3: Moves specified job to the top of the queue.\n" +
                "4: Starts the print server.\n" +
                "5: Stops the print server.\n" +
                "6: Stops the print server, clears the print queue and starts the print server again.\n" +
                "7: Show current status of printer.\n" +
                "8: Show the configuration.\n" +
                "9: Edit the configuration.\n" +
                "10: Log into system.\n" +
                "11: Log out of system.\n" +
                "000: Exit program."
        );
    }

}
