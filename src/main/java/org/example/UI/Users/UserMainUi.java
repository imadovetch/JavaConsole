package org.example.UI.Users;

import java.util.Scanner;

public class UserMainUi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String email = "";
        String choice;

        while (true) {
            System.out.println("1. View Events");
            System.out.println("2. Modify Profile");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    EventsUserMain.Display();
                    break;
                case "2":

                    break;

                case "3":
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
