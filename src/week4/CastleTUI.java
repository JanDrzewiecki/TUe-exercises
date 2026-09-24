package week4;
import java.util.Scanner;

public class CastleTUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String castleName = scanner.nextLine();
        int numberOfCaves = scanner.nextInt();
        CoralCastle coralCastle = new CoralCastle(castleName, numberOfCaves);
        System.out.println("Castle " + castleName + " created with " + numberOfCaves
                + " caves. Type 'help' for commands.");

        boolean running = true;
        while (running) {
            String command = scanner.next();
            switch (command) {
                case "help":
                    System.out.println("Commands:");
                    System.out.println("in [name] [size] - Check in a guest with the given name and size");
                    System.out.println("out [name] - Check out the guest with the given name");
                    System.out.println("cave [name] - Show the cave number of the guest with the name");
                    System.out.println("print - Print the current state of the castle");
                    System.out.println("help - Show this help menu");
                    System.out.println("exit - Exit the program");
                    break;
                case "in":
                    String nameIn = scanner.next();
                    int size = scanner.nextInt();
                    Cave cave = coralCastle.checkIn(nameIn, size);
                    if (cave != null) {
                        System.out.println("Guest " + nameIn + " gets cave "
                                + cave.getNumber() + ".");
                    } else {
                        System.out.println("No suitable cave available for " + nameIn + ".");
                    }
                    break;
                case "out":
                    String nameOut = scanner.next();
                    if (coralCastle.checkOut(nameOut)) {
                        System.out.println(nameOut + " has checked out.");
                    } else {
                        System.out.println("Guest " + nameOut + " is not in the castle.");
                    }
                    break;
                case "cave":
                    String nameCave = scanner.next();
                    Cave guestCave = coralCastle.getCaveByGuestName(nameCave);
                    if (guestCave != null) {
                        System.out.println("Guest " + nameCave + " is in cave "
                                + guestCave.getNumber() + ".");
                    } else {
                        System.out.println("Guest " + nameCave + " doesn't have a cave.");
                    }
                    break;
                case "print":
                    System.out.println(coralCastle);
                    break;
                case "exit":
                    System.out.println("Closing the system.");
                    running = false;
                    break;
                default:
                    // Unknown commands are ignored.
                    break;
            }
        }
        scanner.close();
    }
}
