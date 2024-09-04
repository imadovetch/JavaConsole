package org.example.Database.Controllers.Users;

import org.example.Database.Controllers.AuthController;
import org.example.Database.Models.Events;
import java.util.List;
import java.util.Scanner;

public class EventInscription {
    public String userid;
    public String eventid;
    public String eventname;

    public EventInscription(String userID, String eventID) {
        this.userid = userID;
        this.eventid = eventID;
    }

    public String getEventid() {
        return eventid;
    }

    public String getUserid() {
        return userid;
    }

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
            String choice = scanner.nextLine();
            switch (choice){
                case "quit":
                    break;
                default:
                    try{

                    int x = Integer.parseInt(choice);
                    if (x > 0 && x <= inscriptionEvents.size()) {
                        Events selectedEvent = inscriptionEvents.get(x - 1);
                        CancelInscription(selectedEvent);
                    } else {
                        System.out.println("Invalid choice. Please enter a valid number.");
                    }
                    }catch (Exception e){
                        System.out.println("Pls enter a number or quit");
                    }

            }

        }
    }

    public static void CancelInscription(Events event) {
       Events.InscriptionCancel(event.getId(), AuthController.userid);
    }
}
