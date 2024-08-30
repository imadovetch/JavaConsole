package org.example.Database.Controllers.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.Database.Models.Events;

public class GetEvents {

    public List<Map<String, Object>> fetchData() {
        // Fetch events from the model
        Events eventsModel = new Events();
        List<Events> eventsList = eventsModel.fetcheventsfroDb();

        // Convert events to a list of maps
        List<Map<String, Object>> result = new ArrayList<>();
        for (Events event : eventsList) {
            Map<String, Object> eventMap = new HashMap<>();
            eventMap.put("id", event.getId());
            eventMap.put("name", event.getName());
            eventMap.put("location", event.getLocation());
            eventMap.put("status", event.getStatus());
            eventMap.put("places", event.getPlaces());
            eventMap.put("photo", event.getPhoto());
            result.add(eventMap);
        }

        return result;
    }
}
