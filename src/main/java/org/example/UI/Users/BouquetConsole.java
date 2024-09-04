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
        boolean stop = true;
        while (stop) {
            System.out.print("Enter Eventid UUID To manage it  or > , <, quit): \n");
            input = scanner.nextLine().trim();

            switch (input) {


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

                    stop = false;
                default:


                        Events event = null;
                        for (Events e : Events.eventsList) {
                            if (e.getId().equals(input)) {
                                event = e;
                                break;
                            }
                        }

                        if (event != null) {
                            this.EventsOP(input);
                        } else {
                            System.out.println(" Please enter A valid id ");
                        }

                    break;
            }
        }
    }

    public void EventsOP(String Eventid) {
        boolean status = true;
        while (status) {
            System.out.print("1. To see event Description");
            System.out.print("2. Inscrire event");
            System.out.print("3. quit");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("You chose option 1.");


                    break;
                case "2":

                        Events.Inscrire(AuthController.userid, Eventid);

                    break;
                case "quit":

                    status = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    break;
            }
        }
    }
}
