package org.example.Database.Controllers.Users;

import java.util.HashMap;
import java.util.Map;

public class GetEvents {

    public Map<String, Object> fetchData() {
        Map<String, Object> event = new HashMap<>();

        event.put("id", 1);
        event.put("name", "Music Concert");
        event.put("location", "New York");
        event.put("places", 100);
        event.put("photo", "concert.jpg");
        event.put("status", "signed");
        return event;
    }
    public void GetEventsma() {

        Map<String, Object> event = this.fetchData();

        System.out.println("Event details:");
        for (Map.Entry<String, Object> entry : event.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

