import java.util.Scanner;

public class TUI {
    public static void main(String[] args) {
        System.out.println("Welcome to the printer service. Please enter your desired command-number:\n\n" +
                "1: Print file name on the specified printer.\n" +
                "2: Lists the print queue.\n" +
                "3: Moves specified job to the top of the queue.\n" +
                "4: Starts the print server.\n" +
                "5: Stops the print server.\n" +
                "6: Stops the print server, clears the print queue and starts the print server again.\n" +
                "7: Show current status of printer.\n" +
                "8: Show the configuration.\n" +
                "9: Edit the configuration.\n" +
                "0: Exit program."

        );
        Scanner inputScan = new Scanner(System.in);
        while (!inputScan.hasNextInt()) {
            System.out.println("Input not recognised. Please try again.");
            inputScan.next();
        }
        int correctInput = inputScan.nextInt();

        switch (correctInput) {
            case 1:
                Scanner inputFile = new Scanner(System.in), inputPrinter = new Scanner(System.in);
                System.out.println("Please specify file and printer:");
                String inputFileName = inputFile.next();
                String inputPrinterName = inputPrinter.next();
                System.out.println("Sending file: " + inputFileName + " for printer: " + inputPrinterName);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 0:
                System.out.println("Exiting program...");
                break;
        }
    }

}
