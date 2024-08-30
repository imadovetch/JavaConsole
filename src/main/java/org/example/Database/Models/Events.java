package org.example.Database.Models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.MongoClient;

import org.bson.Document;
import org.example.Utils.DbConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Events {
    private int id;
    private String name;
    private String location;
    private int places;
    private String photo;
    private String status; // New field

    // Default constructor
    public Events() {
    }

    // Constructor with parameters
    public Events(int id, String name, String location, int places, String photo, String status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.places = places;
        this.photo = photo;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", places=" + places +
                ", photo='" + photo + '\'' +
                ", status='" + status + '\'' + // Include status
                '}';
    }


    public void saveEvent() {
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db"); // Replace with your actual database name
        MongoCollection<Document> collection = database.getCollection("events");

        Document eventDocument = new Document("id", id)
                .append("name", name)
                .append("location", location)
                .append("places", places)
                .append("photo", photo)
                .append("status", status); // Added status

        collection.insertOne(eventDocument);
        System.out.println("Event saved to MongoDB: " + toString());
    }

    public static List<Events> fetcheventsfroDb() {
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db"); // Replace with your actual database name
        MongoCollection<Document> collection = database.getCollection("events");

        List<Events> eventsList = new ArrayList<>();
        for (Document doc : collection.find()) {
            Events event = new Events(
                    doc.getInteger("id"),
                    doc.getString("name"),
                    doc.getString("location"),
                    doc.getInteger("places"),
                    doc.getString("photo"),
                    doc.getString("status") // Added status
            );
            eventsList.add(event);
        }

        return eventsList;
    }


    public static List<Events> parseEventsFromString(String eventsString) {
        List<Events> eventsList = new ArrayList<>();

        String[] eventStrings = eventsString.replace("[", "").replace("]", "").split("},\\s*\\{");
        for (String eventStr : eventStrings) {
            eventStr = eventStr.replace("{", "").replace("}", "").replace("\"", "");
            String[] details = eventStr.split(",");
            int id = Integer.parseInt(details[0].split(":")[1].trim());
            String name = details[1].split(":")[1].trim();
            String location = details[2].split(":")[1].trim();
            int places = Integer.parseInt(details[3].split(":")[1].trim());
            String photo = details[4].split(":")[1].trim();
            String status = details[5].split(":")[1].trim(); // Extract status

            Events event = new Events(id, name, location, places, photo, status);
            eventsList.add(event);
        }

        return eventsList;
    }
}
