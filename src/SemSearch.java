
/**
 * Using a BST we will be able to keep track of various seminars each with
 * similar and varying data
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class containing the main method.
 *
 * @author Brantson and Adarsh
 * @version 10.01.2024
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class SemSearch {
    private static Controller control;

    /**
     * @param args
     *            Command line parameters
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        processCommandFile(args[0], args[1]);
    }


    public static void processCommandFile(String worldSize, String fileName)
        throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        control = new Controller();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                if (line.startsWith("insert")) {
                    processInsertCommand(scanner, line, worldSize);
                }
                else {
                    processCommand(line);
                }
            }
        }

        scanner.close();
    }


    public static void processCommand(String command) {
        String[] commandParts = command.split("\\s+");
        String mainCommand = commandParts[0].toLowerCase();

        switch (mainCommand) {
            case "print":
                handlePrint(commandParts);
                break;
            case "search":
                handleSearch(commandParts);
                break;
            case "delete":
                handleDelete(commandParts);
                break;
            default:
                System.out.println("Invalid command: " + mainCommand);
                break;
        }
    }


    private static void processInsertCommand(
        Scanner scanner,
        String firstLine,
        String worldSize) {
        String[] commandParts = firstLine.split("\\s+");
        int id = Integer.parseInt(commandParts[1]);

        String title = scanner.nextLine().trim();
        String dateTimeLine = scanner.nextLine().trim();
        String[] dateTimeParts = dateTimeLine.split("\\s+");
        String dateTime = dateTimeParts[0];
        int length = Integer.parseInt(dateTimeParts[1]);
        short x = Short.parseShort(dateTimeParts[2]);
        short y = Short.parseShort(dateTimeParts[3]);
        int cost = Integer.parseInt(dateTimeParts[4]);
        String[] keywords = scanner.nextLine().trim().split("\\s+");
        String description = scanner.nextLine().trim();

        Seminar sem = new Seminar(id, title, dateTime, length, x, y, cost,
            keywords, description);
        control.insert(sem, worldSize);
    }


    private static void handleDelete(String[] commandParts) {
        int id = Integer.parseInt(commandParts[1]);
        control.delete(id);
    }


    private static void handleSearch(String[] commandParts) {
        String searchType = commandParts[1].toLowerCase();

        switch (searchType) {
            case "id":
                int id = Integer.parseInt(commandParts[2]);
                control.searchID(id);
                break;
            case "keyword":
                String keyword = commandParts[2];
                control.searchKeyword(keyword);
                break;
            case "cost":
                int low = Integer.parseInt(commandParts[2]);
                int high = Integer.parseInt(commandParts[3]);
                control.searchRange(low, high);
                break;
            case "location":
                short x = Short.parseShort(commandParts[2]);
                short y = Short.parseShort(commandParts[3]);
                int radius = Integer.parseInt(commandParts[4]);
                // Search by location logic
                break;
            case "date":
                String startDate = commandParts[2];
                String endDate = commandParts[3];
                control.searchRange(startDate, endDate);
                break;
            default:
                System.out.println("Invalid search type: " + searchType);
                break;
        }
    }


    private static void handlePrint(String[] commandParts) {
        String printType = commandParts[1].toLowerCase();

        switch (printType) {
            case "id":
                control.printId();
                break;
            case "keyword":
                control.printKeyword();
                break;
            case "location":
                // Print by location logic
                break;
            case "cost":
                control.printCost();
                break;
            case "date":
                control.printDates();
                break;
            default:
                System.out.println("Invalid print type: " + printType);
                break;
        }
    }
}
