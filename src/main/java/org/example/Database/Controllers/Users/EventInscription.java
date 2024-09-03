package org.example.Database.Controllers.Users;

import org.example.Database.Models.Events;
import java.util.List;
import java.util.Scanner;

public class EventInscription {

    public static void DisplayInscriptions() {
        List<Events> inscriptionEvents = Events.GetInscriptions();

        if (inscriptionEvents.isEmpty()) {
            System.out.println("No inscriptions found.");
        } else {
            System.out.println("Inscriptions:");
            for (int i = 0; i < inscriptionEvents.size(); i++) {
                Events event = inscriptionEvents.get(i);
                System.out.println((i + 1) + ". Event ID: " + event.getId());
                System.out.println("   Name: " + event.getName());
                System.out.println("   Location: " + event.getLocation());
                System.out.println("   Places: " + event.getPlaces());
                System.out.println("   Photo: " + event.getPhoto());
                System.out.println("   Status: " + event.getStatus());
                System.out.println("---------------------------");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the inscription to cancel: ");
            int choice = scanner.nextInt();

            if (choice > 0 && choice <= inscriptionEvents.size()) {
                Events selectedEvent = inscriptionEvents.get(choice - 1);
                CancelInscription(selectedEvent);
            } else {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    public static void CancelInscription(Events event) {
       Events.InscriptionCancel(event.getId());
    }
}
