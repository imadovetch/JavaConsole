package org.example.Database.Controllers.Admin;

import org.example.Database.Models.Events;

import java.util.Map;

public class AddEventcot {

    public AddEventcot() {
    }

    public void addEventToDatabase(Map<String, Object> event) {
        // Extract data from the map
        String name = (String) event.get("name");
        String location = (String) event.get("location");
        int places = (Integer) event.get("places");
        String photo = (String) event.get("photo");
        String status = (String) event.get("status");
        // Create an Events object with the extracted data
        Events eventModel = new Events();
        eventModel.setName(name);
        eventModel.setLocation(location);
        eventModel.setPlaces(places);
        eventModel.setPhoto(photo);
        eventModel.setStatus(status);
        // Save the event data
        eventModel.saveEvent();

    }
}
