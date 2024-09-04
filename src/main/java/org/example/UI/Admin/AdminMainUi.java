package org.example.UI.Admin;

import org.example.Database.Models.Events;
import org.example.Database.Controllers.Users.EventInscription;
import org.example.Database.Models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Database.Models.Events.GetALLInscriptions;

public class AdminMainUi {
    private static Scanner scanner = new Scanner(System.in);

    private static List<Users> dummyUsers = Users.fetchUsers();


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
        System.out.println("2. Add Events");
        System.out.println("3. Modify Events");
        System.out.println("S. To see stats");
        System.out.println("4. Delete Event");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                showEvents();
                break;
            case "2":
                AddEventUi.main(null);
                break;
            case "3":
                ModifyEvent();
                break;
            case "4":
                deleteEvent();
                break;
            case "S":
//                ShowStats();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void showEvents() {
        List<Events> dummyEvents = Events.fetcheventsfroDb();
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
        List<Events> dummyEvents = Events.fetcheventsfroDb();

        System.out.print("Enter the ID of the event to delete: ");
        String id = scanner.nextLine().trim();
        Events.Delete(id);
        dummyEvents.removeIf(event -> event.getId().equals(id));

    }
    private static void ModifyEvent() {
        List<Events> dummyEvents = Events.fetcheventsfroDb();

        System.out.print("Enter the ID of the event to modify: ");
        String id = scanner.nextLine().trim();


        Events eventToModify = null;
        for (Events event : dummyEvents) {
            if (event.getId().equals(id)) {
                eventToModify = event;
                break;
            }
        }

        if (eventToModify != null) {
            System.out.print("Enter new name (current: " + eventToModify.getName() + "): ");
            String newName = scanner.nextLine().trim();

            System.out.print("Enter new location (current: " + eventToModify.getLocation() + "): ");
            String newLocation = scanner.nextLine().trim();

            System.out.print("Enter new number of places (current: " + eventToModify.getPlaces() + "): ");
            int newPlaces = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter new photo path (current: " + eventToModify.getPhoto() + "): ");
            String newPhoto = scanner.nextLine().trim();

            System.out.print("Enter new status (current: " + eventToModify.getStatus() + "): ");
            String newStatus = scanner.nextLine().trim();

            eventToModify.setName(newName);
            eventToModify.setLocation(newLocation);
            eventToModify.setPlaces(newPlaces);
            eventToModify.setPhoto(newPhoto);
            eventToModify.setStatus(newStatus);

            Events.Updateevent(eventToModify);

            System.out.println("Event updated successfully.");
        } else {
            System.out.println("Event with the given ID not found.");
        }
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
                showInscriptions();
                break;
            case "2":
                cancelInscription();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void showInscriptions() {
        System.out.println("Inscriptions:");

        List<EventInscription> inscriptions = GetALLInscriptions();
        if (inscriptions.isEmpty()) {
            System.out.println("No inscriptions found.");
        } else {
            for (EventInscription inscription : inscriptions) {
                System.out.println("User ID: " + inscription.userid + ", Event ID: " + inscription.eventid);
            }
        }
    }

    private static void cancelInscription() {
        System.out.print("Enter the event ID and userid ");
        String eventId = scanner.nextLine().trim();
        String userid = scanner.nextLine().trim();
        Events.InscriptionCancel(userid,eventId);
    }
    public void ShowStats(){
        System.out.println("Stats:");

//        Events.GetStats();
//        Users.Getstats();
    }
}
