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
    public List<Events> eventsList;
    public  GetEvents() {
        this.eventsList = null;
    }
    public List<Events> fetchData() {

        Events eventsModel = new Events();
        eventsList = eventsModel.fetcheventsfroDb();

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
           this.StockFetcheddata(event);
        }
        count = 0;
        return eventsList;
    }


    private void StockFetcheddata(Events event) {
        storedEvents.add(event);
    }



}
