package rmi;

import login.Token;
import rmi.commands.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class NewRMIClient {

    private Token token = new Token("HackerMan", "Username");
    private Scanner inputScan = new Scanner(System.in);
    private LoginCommand loginCommand = new LoginCommand();
    private PrintServerInterface printServer;

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        new NewRMIClient().run();
    }

    private void run() throws RemoteException, NotBoundException, MalformedURLException {
        printServer = connectToServer();

        //Attempt to log in.

        while (true) {
            try {
                token = loginCommand.run(token, printServer);
                printTUI();
                handleUserInput();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PrintServerInterface connectToServer() throws RemoteException, NotBoundException, MalformedURLException {
        return (PrintServerInterface) Naming.lookup("rmi://localhost:5099/rmiserver");
    }

    private void handleUserInput() {
        String userInput;
        while (true) {
            try {
                userInput = inputScan.nextLine().toLowerCase();
                switch (userInput) {
                    case "print":
                        PrintCommand printCommand = new PrintCommand();
                        printCommand.run(token, printServer);
                        break;
                    case "queue":
                        QueueCommand queueCommand = new QueueCommand();
                        queueCommand.run(token, printServer);
                        break;
                    case "topqueue":
                        TopQueueCommand topQueueCommand = new TopQueueCommand();
                        topQueueCommand.run(token, printServer);
                        break;
                    case "start":
                        StartCommand startCommand = new StartCommand();
                        startCommand.run(token, printServer);
                        break;
                    case "stop":
                        StopCommand stopCommand = new StopCommand();
                        stopCommand.run(token, printServer);
                        break;
                    case "restart":
                        RestartCommand restartCommand = new RestartCommand();
                        restartCommand.run(token, printServer);
                        break;
                    case "status":
                        StatusCommand statusCommand = new StatusCommand();
                        statusCommand.run(token, printServer);
                        break;
                    case "showconfig":
                        ShowConfigCommand showConfigCommand = new ShowConfigCommand();
                        showConfigCommand.run(token, printServer);
                        break;
                    case "editconfig":
                        EditConfigCommand editConfigCommand = new EditConfigCommand();
                        editConfigCommand.run(token, printServer);
                        break;
                    case "login":
                        LoginCommand loginCommand = new LoginCommand();
                        token = loginCommand.run(token, printServer);
                        printTUI();
                        break;
                    case "logout":
                        LogOutCommand logOutCommand = new LogOutCommand();
                        logOutCommand.run(token, printServer);
                        break;
                    case "help":
                        printTUI();
                        break;
                    case "exit":
                        System.out.println("Exiting");
                        return;
                    default:
                        System.out.println("Input not recognised. Please try again");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // TODO: 18-11-2018 Insert a dynamically created TUI.
    // TODO: Use the getUserPermission method and check if it contains proper permissions.
    private void printTUI() {
        // Verify valid token value for user!
        System.out.println(token.toString());

        System.out.println("\n\nWelcome to the printer service. Please enter your desired command.\n" +
                "'print': Print file name on the specified printer.\n" +
                "'queue': Lists the print queue.\n" +
                "'topqueue': Moves specified job to the top of the queue.\n" +
                "'start': Starts the print server.\n" +
                "'stop': Stops the print server.\n" +
                "'restart': Stops the print server, clears the print queue and starts the print server again.\n" +
                "'status': Show current status of printer.\n" +
                "'showconfig': Show the configuration.\n" +
                "'editconfig': Edit the configuration.\n" +
                "'login': Log into the system again.\n" +
                "'logout': Log out of system.\n" +
                "'help': Print the command list again.\n" +
                "'exit': Exit program.\n");

    }
}
