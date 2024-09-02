package org.example;


import org.example.Database.Controllers.Users.ConnectTest;
import org.example.UI.Admin.AddEventUi;
import org.example.UI.Home.AuthUI;
import org.example.UI.Users.Bouquet;
import org.example.UI.Users.BouquetConsole;
import org.example.Utils.DbConnection;
import org.example.Utils.PhotoOpener;



public class Main {

    public Main(){

    }
    public static void main(String[] args) {

       // PhotoOpener PhotoOpener = new PhotoOpener("C:\\Users\\ycode\\Downloads\\testp.png");
//    while(true){
   new AuthUI().DisplayUI();
       // new Bouquet();
   // new BouquetConsole();
//       // new ConnectTest();
//    AddEventUi.main(null);
//    }

    }
}
