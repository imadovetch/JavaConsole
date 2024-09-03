package org.example.UI.Users;

import org.example.Database.Controllers.AuthController;
import org.example.Database.Controllers.Users.GetEvents;
import org.example.Database.Models.Events;

import java.util.Scanner;

public class BouquetConsole {
    Scanner scanner = new Scanner(System.in);

    public BouquetConsole() {
        String input;
        Bouquet.getInstance().DisplayBuquet();
        while (true) {
            System.out.print("Enter your choice (1, 2, 3, 4, >, <, quit): ");
            input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("You chose option 1.");
                    this.EventsOP(1);
                    break;
                case "2":
                    System.out.println("You chose option 2.");
                    this.EventsOP(2);
                    break;
                case "3":
                    System.out.println("You chose option 3.");
                    this.EventsOP(3);
                    break;
                case "4":
                    System.out.println("You chose option 4.");
                    this.EventsOP(4);
                    break;
                case ">":
                    System.out.println("You chose next.");
                    GetEvents.page++;
                    Bouquet.getInstance().DisplayBuquet();
                    break;
                case "<":
                    System.out.println("You chose previous.");
                    GetEvents.page--;
                    Bouquet.getInstance().DisplayBuquet();
                    break;
                case "quit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return; // Exit the main method
                default:
                    System.out.println("Invalid input. Please enter 1, 2, 3, 4, >, <, or quit.");
                    break;
            }
        }
    }

    public void EventsOP(int Eventid) {
        Bouquet.getInstance().DisplayBuquet();
        boolean status = true;
        while (status) {
            System.out.print("1. To see event Description");
            System.out.print("2. Inscrire event");
            System.out.print("3. quit");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("You chose option 1.");
                    // Assuming input is a number, parse it to an integer


                    break;
                case "2":
                    int index = Integer.parseInt(input);
                    Events event = Events.eventsList.get(index - 1);
                    if (index > 0 && index <= Events.eventsList.size()) {
                        Events.Inscrire(AuthController.userid, event.getId());

                    } else {
                        System.out.println("Invalid event index.");
                    }

                    break;
                case "quit":
                    Bouquet.getInstance().DisplayBuquet();
                    status = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    break;
            }
        }
    }
}
