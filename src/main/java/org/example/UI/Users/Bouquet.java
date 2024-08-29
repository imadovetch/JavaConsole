package org.example.UI.Users;

import org.example.Database.Controllers.Users.GetEvents;
import java.util.Map;

public class Bouquet {

    public Bouquet() {
        GetEvents getEvents = new GetEvents();
        Map<String, Object> event = getEvents.fetchData();

        // Print the event details
        System.out.println("Event details:");
        for (Map.Entry<String, Object> entry : event.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        new Bouquet();
    }
}
