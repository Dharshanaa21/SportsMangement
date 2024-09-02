package sports;

import java.util.Scanner;

public class SportsManagementSystem {
    private static Athlete athleteManager = new Athlete();
    private static Event eventManager = new Event();
    private static Result resultManager = new Result();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Sports Event Management System");
            System.out.println("1. Manage Athletes");
            System.out.println("2. Manage Events");
            System.out.println("3. Manage Results");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageAthletes(scanner);
                    break;
                case 2:
                    manageEvents(scanner);
                    break;
                case 3:
                    manageResults(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void manageAthletes(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nAthlete Management");
            System.out.println("1. Add Athlete");
            System.out.println("2. View Athlete");
            System.out.println("3. Update Athlete");
            System.out.println("4. Remove Athlete");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    athleteManager.addAthlete();
                    break;
                case 2:
                    athleteManager.viewAthlete();
                    break;
                case 3:
                    athleteManager.updateAthlete();
                    break;
                case 4:
                    athleteManager.removeAthlete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void manageEvents(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nEvent Management");
            System.out.println("1. Add Event");
            System.out.println("2. View Event");
            System.out.println("3. Update Event");
            System.out.println("4. Remove Event");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    eventManager.addEvent();
                    break;
                case 2:
                    eventManager.viewEvent();
                    break;
                case 3:
                    eventManager.updateEvent();
                    break;
                case 4:
                    eventManager.removeEvent();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void manageResults(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nResult Management");
            System.out.println("1. Add Result");
            System.out.println("2. View Result");
            System.out.println("3. Update Result");
            System.out.println("4. Remove Result");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    resultManager.addResult();
                    break;
                case 2:
                    resultManager.viewResult();
                    break;
                case 3:
                    resultManager.updateResult();
                    break;
                case 4:
                    resultManager.removeResult();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
