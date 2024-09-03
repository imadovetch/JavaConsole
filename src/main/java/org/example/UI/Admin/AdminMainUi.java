package org.example.UI.Admin;

import org.example.Database.Models.Events;
import org.example.Database.Controllers.Users.EventInscription;
import org.example.Database.Models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMainUi {
    private static Scanner scanner = new Scanner(System.in);

    private static List<Users> dummyUsers = Users.fetchUsers();
    private static List<Events> dummyEvents = Events.fetcheventsfroDb();


    public static void DisplayUI() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Events");
            System.out.println("2. Manage Clients");
            System.out.println("3. Manage Inscriptions");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    manageEvents();
                    break;
                case "2":
                    manageClients();
                    break;
                case "3":
                    manageInscriptions();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                    break;
            }
        }
    }

    private static void manageEvents() {
        System.out.println("Manage Events:");
        System.out.println("1. Show Events");
        System.out.println("2. Delete Event");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                showEvents();
                break;
            case "2":
                deleteEvent();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void showEvents() {
        System.out.println("Events:");
        for (Events event : dummyEvents) {
            System.out.println("ID: " + event.getId());
            System.out.println("Name: " + event.getName());
            System.out.println("Location: " + event.getLocation());
            System.out.println("Places: " + event.getPlaces());
            System.out.println("Photo: " + event.getPhoto());
            System.out.println("Status: " + event.getStatus());
            System.out.println("---------------------------");
        }
    }

    private static void deleteEvent() {
        System.out.print("Enter the ID of the event to delete: ");
        String id = scanner.nextLine().trim();
        Events.Delete(id);
        dummyEvents.removeIf(event -> event.getId().equals(id));

    }

    private static void manageClients() {
        System.out.println("Manage Clients:");
        System.out.println("1. Display Users");
        System.out.println("2. Delete User");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                displayUsers();
                break;
            case "2":
                deleteUser();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void displayUsers() {
        System.out.println("Users:");
        for (Users user : dummyUsers) {
            System.out.println("Email: " + user.getEmail());
            System.out.println("Name: " + user.getPassword());
            System.out.println("---------------------------");
        }
    }

    private static void deleteUser() {
        System.out.print("Enter the email of the user to delete: ");
        String email = scanner.nextLine().trim();
        Users.Delete(email);
        dummyUsers.removeIf(user -> user.getEmail().equals(email));
        System.out.println("User with email " + email + " has been deleted.");
    }

    private static void manageInscriptions() {
        System.out.println("Manage Inscriptions:");
        System.out.println("1. Show Inscriptions");
        System.out.println("2. Cancel Inscription");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
//                showInscriptions();
                break;
            case "2":
                cancelInscription();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

//    private static void showInscriptions() {
//        System.out.println("Inscriptions:");
//
//        List<EventInscription> inscriptions = new ArrayList<>();
//        for (EventInscription inscription : inscriptions) {
//            System.out.println("Email: " + inscription.getEmail());
//            System.out.println("Event Name: " + inscription.getEventName());
//            System.out.println("Event ID: " + inscription.getEventId());
//            System.out.println("---------------------------");
//        }
//    }

    private static void cancelInscription() {
        System.out.print("Enter the event ID of the inscription to cancel: ");
        String eventId = scanner.nextLine().trim();

        System.out.println("Inscription with event ID " + eventId + " has been canceled.");
    }
}
