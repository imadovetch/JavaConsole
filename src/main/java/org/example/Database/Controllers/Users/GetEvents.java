package org.example.Database.Controllers.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.Database.Models.Events;

public class GetEvents {
    public static int count = 4;
    public static  int page = 1;
    public static  int Currentid ;

    public static List<Events> storedEvents = new ArrayList<>();

    public String getUuidById(String id) {
        for (Events event : storedEvents) {
            if (event.getId().equals(id)) {
                return event.getId();
            }
        }
        return null;
    }
    public List<Map<String, Object>> fetchData() {

        Events eventsModel = new Events();
        List<Events> eventsList = eventsModel.fetcheventsfroDb();

        List<Map<String, Object>> result = new ArrayList<>();
        int count = 1;
        for (Events event : eventsList) {
            Map<String, Object> eventMap = new HashMap<>();
            eventMap.put("id", count);
            eventMap.put("uuid", event.getId());
            eventMap.put("name", event.getName());
            eventMap.put("location", event.getLocation());
            eventMap.put("status", event.getStatus());
            eventMap.put("places", event.getPlaces());
            eventMap.put("photo", event.getPhoto());
            result.add(eventMap);
            count++;
//            System.out.println("h");
//            System.out.println(event.getId());
           this.StockFetcheddata(event);
        }
        count = 0;
        return result;
    }


    private void StockFetcheddata(Events event) {
        storedEvents.add(event);
    }

    public List<Events> getStoredEvents() {
        return storedEvents;
    }

}
