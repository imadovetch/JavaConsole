package org.example.UI.Users;

import org.example.Database.Controllers.Users.EventInscription;
import org.example.Database.Models.Users;

import java.util.Scanner;

public class UserMainUi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("1. View Events");
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
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                    break;
            }
        }
    }


}
