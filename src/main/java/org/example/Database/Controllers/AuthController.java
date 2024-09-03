package org.example.Database.Controllers;

import org.example.Database.Models.Users;

public class AuthController {
            private String email;
            private String password;
            public static String userid;
            public AuthController(String email, String password) {
//                validate
                this.email = email;
                this.password = password;


            }


            public   int register(){
                        Users.register(this.email, this.password);
                return 1;
            }
            public boolean login(){

                if(Users.login(this.email, this.password)){

                    return true;
                };
                return false;
            }


}
