package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class RMIClient {
    String token = "Hackerman";
    Scanner inputScan = new Scanner(System.in);
    PrintServerInterface printerServer;

    public static void main(String[] args) throws Exception {
        new RMIClient().run();
    }

    private void run() throws RemoteException, NotBoundException, MalformedURLException {
        printerServer =connectToServer();
        printWelcomeMessage();
        handleUserInput();
    }

    private PrintServerInterface connectToServer() throws RemoteException, NotBoundException, MalformedURLException {
        return (PrintServerInterface) Naming.lookup("rmi://localhost:5099/rmiserver");
    }

    private void handleUserInput(){
        String correctInput;
        while (true){
            try {
                correctInput = inputScan.nextLine();
                switch (correctInput) {
                    case "1":
                        System.out.println("Please specify file and printer:");
                        String inputFileName = inputScan.nextLine();
                        String inputPrinterName = inputScan.nextLine();
                        System.out.println("Send file: " + inputFileName + " for printer: " + inputPrinterName);
                        printerServer.print(token, inputFileName, inputPrinterName);
                        break;
                    case "2":
                        System.out.println(printerServer.queue(token));
                        break;
                    case "3":
                        System.out.println("Please specify fileindex to push to top of queue.");
                        int inputJobIndex = Integer.parseInt(inputScan.nextLine());
                        printerServer.topQueue(token, inputJobIndex);
                        System.out.println("Pushed file index: " + inputJobIndex + " to top of queue.");
                        break;
                    case "4":
                        printerServer.start(token);
                        System.out.println("Printer Started");
                        break;
                    case "5":
                        printerServer.stop(token);
                        System.out.println("Printer Stopped");
                        break;
                    case "6":
                        printerServer.restart(token);
                        System.out.println("Printer Reset");
                        break;
                    case "7":
                        System.out.println("Printer status: "+printerServer.status(token));
                        break;
                    case "8":
                        System.out.println("Please specify config to read.");
                        String inputConfigName = inputScan.nextLine();
                        System.out.println(inputConfigName+": "+printerServer.readConfig(token, inputConfigName));
                        break;
                    case "9":
                        System.out.println("Please specify config to read and new value.");
                        String inputEditConfigName = inputScan.nextLine();
                        String inputEditConfigNewText = inputScan.nextLine();
                        printerServer.setConfig(token, inputEditConfigName, inputEditConfigNewText);
                        break;
                    case "10":
                        System.out.println("Please specify username and password.");
                        String inputUserName = inputScan.nextLine();
                        String inputUserPass = inputScan.nextLine();
                        printerServer.login(inputUserName, inputUserPass);
                        break;
                    case "11":
                        System.out.println("Logging out of user.");
                        printerServer.logout(token);
                        break;
                    case "000":
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Input not recognised. Please try again");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
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
