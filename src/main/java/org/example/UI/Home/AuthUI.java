package org.example.UI.Home;

import org.example.Database.Controllers.AuthController;
import org.example.UI.Users.UserMainUi;
import org.example.Utils.HandleErrors;

import java.util.Scanner;

public class AuthUI {
    private String email;
    private String password;
    public AuthUI(){

    }

    public AuthUI(String s, String pass) {
    }

    public void   DisplayUI(){

        Scanner scanner = new Scanner(System.in);
        String choice;

        while (!HandleErrors.quit) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    this.scan();
                    new AuthController(this.email, this.password).register();
                    break;
                case "2":
                    this.scan();
                    if(new AuthController(this.email, this.password).login()){

                        UserMainUi.main(null);
                    };
                    break;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    HandleErrors.quit = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        }



    }
    private  void scan(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your email: ");
         this.email = scanner.nextLine().trim();
        System.out.print("Enter your password: ");
         this.password = scanner.nextLine().trim();

    }

}
