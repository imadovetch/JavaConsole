package org.example.UI.Users;

import org.example.Database.Controllers.Users.EventInscription;
import org.example.Database.Models.Events;
import org.example.Database.Models.Users;

import java.util.List;
import java.util.Scanner;

public class UserMainUi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("1. View Events");
            System.out.println("5. Search Event");
            System.out.println("2. View Inscriptions");
            System.out.println("3. Modify Profile");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    EventsUserMain.Display();
                    break;
                case "2":
                    EventInscription.DisplayInscriptions();
                    break;
                case "3":
                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.nextLine().trim();
                    // Call a method to update the user's password
                    Users.modifyUserProfile(newPassword);

                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                case "5":
                    SearchEvent();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                    break;
            }
        }
    }
    public static void SearchEvent() {

        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.print("Enter your choice (1 for Location, 2 for Type ,3 for date, 'quit' to exit): ");
            choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter the location to search for: ");
                String location = scanner.nextLine();
                searchByLocation(location);
            } else if (choice.equals("2")) {
                System.out.print("Enter the status to search for: ");
                String status = scanner.nextLine();
                searchByStatus(status);
            } else if (choice.equals("3")) {
                System.out.print("Enter the Date Like yyyy/mm/dd: ");
                String status = scanner.nextLine();
                searchByDate(status);
            }
            else if (choice.equalsIgnoreCase("quit")) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 'quit'.");
            }
        }

    }

    private static void searchByLocation(String location) {
        List<Events> eventsList = Events.eventsList;

        boolean found = false;
        for (Events event : eventsList) {
            if (event.getLocation().equalsIgnoreCase(location)) {
                System.out.println("Found event: " + event.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No events found for location: " + location);
        }
    }

    private static void searchByStatus(String status) {
        List<Events> eventsList = Events.eventsList;

        boolean found = false;
        for (Events event : eventsList) {
            if (event.getStatus().equalsIgnoreCase(status)) {
                System.out.println("Found event: " + event.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No events found with status: " + status);
        }
    }

    private static void searchByDate(String status) {
        List<Events> eventsList = Events.eventsList;

        boolean found = false;
        for (Events event : eventsList) {
            if (event.getPhoto().equalsIgnoreCase(status)) {
                System.out.println("Found event: " + event.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No events found with status: " + status);
        }
    }


}
