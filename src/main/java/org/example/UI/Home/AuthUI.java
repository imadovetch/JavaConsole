package org.example.UI.Home;

import org.example.Database.Controllers.AuthController;
import org.example.Database.Models.Users;
import org.example.UI.Admin.AdminMainUi;
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
                    asci();
                    break;
                case "2":
                    this.scan();
                    if(new AuthController(this.email, this.password).login()){

                        asci();
                        if (Users.Role.equals("admin"))
                            AdminMainUi.DisplayUI();
                        else
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
    private void scan() {
        Scanner scanner = new Scanner(System.in);
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        while (true) {
            System.out.print("Enter your email: ");
            this.email = scanner.nextLine().trim();

            if (this.email.matches(emailRegex)) {
                break;
            } else {
                System.out.println("Invalid email format! Please enter a valid email.");
            }
        }

        System.out.print("Enter your password: ");
        this.password = scanner.nextLine().trim();
    }

    public  void asci(){
        System.out.println("                                                                                                              \n" +
                "         eeeeeeeeeeee    vvvvvvv           vvvvvvv    eeeeeeeeeeee    nnnn  nnnnnnnn    ttttttt:::::ttttttt         \n" +
                "       ee::::::::::::ee   v:::::v         v:::::v   ee::::::::::::ee  n:::nn::::::::nn  t:::::::::::::::::t         \n" +
                "      e::::::eeeee:::::ee  v:::::v       v:::::v   e::::::eeeee:::::een::::::::::::::nn t:::::::::::::::::t         \n" +
                "     e::::::e     e:::::e   v:::::v     v:::::v   e::::::e     e:::::enn:::::::::::::::ntttttt:::::::tttttt         \n" +
                "     e:::::::eeeee::::::e    v:::::v   v:::::v    e:::::::eeeee::::::e  n:::::nnnn:::::n      t:::::t               \n" +
                "     e:::::::::::::::::e      v:::::v v:::::v     e:::::::::::::::::e   n::::n    n::::n      t:::::t               \n" +
                "     e::::::eeeeeeeeeee        v::::✌\uFE0F::::v      e::::::eeeeeeeeeee    n::::n    n::::n      t:::::t               \n" +
                "     e:::::::e                  v:::::::::v       e:::::::e             n::::n    n::::n      t:::::t    tttttt     \n" +
                "     e::::::::e                  v:::::::v        e::::::::e            n::::n    n::::n      t::::::tttt:::::t     \n" +
                "      e::::::::eeeeeeee           v:::::v          e::::::::eeeeeeee    n::::n    n::::n      tt::::::::::::::t     \n" +
                "       ee:::::::::::::e            v:::v            ee:::::::::::::e    n::::n    n::::n        tt:::::::::::tt     \n" +
                "         eeeeeeeeeeeeee             vvv               eeeeeeeeeeeeee    nnnnnn    nnnnnn          ttttttttttt       \n" +
                "kkkkkkkk                                                                                                               \n" +
                "k::::::k                                                                                                               \n" +
                "k::::::k                                                                                                               \n" +
                "k::::::k                                                                                                               \n" +
                " k:::::k    kkkkkkk    eeeeeeeeeeee        eeeeeeeeeeee    ppppp   ppppppppp       eeeeeeeeeeee    rrrrr   rrrrrrrrr   \n" +
                " k:::::k   k:::::k   ee::::::::::::ee    ee::::::::::::ee  p::::ppp:::::::::p    ee::::::::::::ee  r::::rrr:::::::::r  \n" +
                " k:::::k  k:::::k   e::::::eeeee:::::ee e::::::eeeee:::::eep:::::::::::::::::p  e::::::eeeee:::::eer:::::::::::::::::r \n" +
                " k:::::k k:::::k   e::::::e     e:::::ee::::::e     e:::::epp::::::ppppp::::::pe::::::e     e:::::err::::::rrrrr::::::r\n" +
                " k::::::k:::::k    e:::::::eeeee::::::ee:::::::eeeee::::::e p:::::p     p:::::pe:::::::eeeee::::::e r:::::r     r:::::r\n" +
                " k:::::::::::k     e:::::::::::::::::e e:::::::::::::::::e  p:::::p     p:::::pe:::::::::::::::::e  r:::::r     rrrrrrr\n" +
                " k:::::::::::k     e::::::eeeeeeeeeee  e::::::eeeeeeeeeee   p:::::p     p:::::pe::::::eeeeeeeeeee   r:::::r            \n" +
                " k::::::k:::::k    e:::::::e           e:::::::e            p:::::p    p::::::pe:::::::e            r:::::r            \n" +
                "k::::::k k:::::k   e::::::::e          e::::::::e           p:::::ppppp:::::::pe::::::::e           r:::::r            \n" +
                "k::::::k  k:::::k   e::::::::eeeeeeee   e::::::::eeeeeeee   p::::::::::::::::p  e::::::::eeeeeeee   r:::::r            \n" +
                "k::::::k   k:::::k   ee:::::::::::::e    ee:::::::::::::e   p::::::::::::::pp    ee:::::::::::::e   r:::::r            \n" +
                "kkkkkkkk    kkkkkkk    eeeeeeeeeeeeee      eeeeeeeeeeeeee   p::::::pppppppp        eeeeeeeeeeeeee   rrrrrrr            \n" +
                "                                                            p:::::p                                                    \n" +
                "                                                            p:::::p                                                    \n" +
                "                                                           p:::::::p                                                   ");
    }
}
