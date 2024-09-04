package org.example.Database.Models;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.example.Database.Controllers.AuthController;
import org.example.Database.Controllers.Users.EventInscription;
import org.example.Database.Controllers.Users.GetEvents;
import org.example.Utils.DbConnection;
import org.example.Utils.HandleErrors;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Events {

    protected String id;
    private String name;
    private String location;
    private int places;
    private String photo; // type
    private String status; // date
    public static List<Events> eventsList = new ArrayList<>();

    public Events() {
    }


    public Events(String id, String name, String location, int places, String photo, String status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.places = places;
        this.photo = photo;
        this.status = status;
    }

    public static void Updateevent(Events eventToModify) {


        MongoCollection<Document> collection = GetConnection().getCollection("events");

        Document filter = new Document("id", eventToModify.getId());
        Document update = new Document("$set", new Document()
                .append("name", eventToModify.getName())
                .append("location", eventToModify.getLocation())
                .append("places", eventToModify.getPlaces())
                .append("photo", eventToModify.getPhoto())
                .append("status", eventToModify.getStatus()));

        collection.updateOne(filter, update);
        System.out.println("Event updated in MongoDB: " + eventToModify);
    }


        public static List<EventInscription> GetALLInscriptions() {
            List<EventInscription> inscriptionsList = new ArrayList<>();



            MongoCollection<Document> inscriptionCollection = GetConnection().getCollection("Inscriptions");

            FindIterable<Document> documents = inscriptionCollection.find();

            for (Document doc : documents) {
                String userID = doc.getString("userID");
                String eventID = doc.getString("eventID");

                EventInscription inscription = new EventInscription(userID, eventID);
                inscriptionsList.add(inscription);
            }

            return inscriptionsList;
        }



    public void saveEvent() {


        MongoCollection<Document> collection = GetConnection().getCollection("events");

        Document eventDocument = new Document("id", UUID.randomUUID().toString())
                .append("name", name)
                .append("location", location)
                .append("places", places)
                .append("photo", photo)
                .append("status", status); // Added status

        collection.insertOne(eventDocument);
        System.out.println("Event saved to MongoDB: " + toString());
    }

    public static List<Events> fetcheventsfroDb() {
        int count = GetEvents.count;
        int page = GetEvents.page;


        MongoCollection<Document> collection = GetConnection().getCollection("events");

        int skipCount = (page - 1) * count;

        eventsList.clear();
        for (Document doc : collection.find()
//                .skip(skipCount)
//                .limit(count)
        ) {
            Events event = new Events(
                    doc.getString("id"),
                    doc.getString("name"),
                    doc.getString("location"),
                    doc.getInteger("places"),
                    doc.getString("photo"),
                    doc.getString("status")
            );

            eventsList.add(event);
        }

        return eventsList;
    }


    public static boolean Inscrire(String userID, String eventID) {


        MongoCollection<Document> collection = GetConnection().getCollection("Inscriptions");

        if (CheckInscriptionExist(userID, eventID)) {
            HandleErrors.DisplayError("Inscription already exists.");

            return false;
        }

        Document newInscription = new Document("userID", userID)
                .append("eventID", eventID);
        collection.insertOne(newInscription);

        HandleErrors.DisplayError("Inscription added successfully.");
        return true;
    }

    private static boolean CheckInscriptionExist(String userID, String eventID) {


        MongoCollection<Document> collection = GetConnection().getCollection("Inscriptions");

        Document query = new Document("userID", userID)
                .append("eventID", eventID);

        long count = collection.countDocuments(query);
        return count > 0;
    }

    public static List<Events> GetInscriptions() {
        List<Events> eventsList = new ArrayList<>();



        MongoCollection<Document> inscriptionCollection = GetConnection().getCollection("Inscriptions");


        List<String> eventIds = new ArrayList<>();
        for (Document doc : inscriptionCollection.find(new Document("userID", AuthController.userid))) {
            eventIds.add(doc.getString("eventID"));
        }


        MongoCollection<Document> eventsCollection = GetConnection().getCollection("events");
        for (String eventId : eventIds) {
            Document eventDoc = eventsCollection.find(new Document("id", eventId)).first();
            if (eventDoc != null) {
                Events event = new Events(
                        eventDoc.getString("id"),
                        eventDoc.getString("name"),
                        eventDoc.getString("location"),
                        eventDoc.getInteger("places"),
                        eventDoc.getString("photo"),
                        eventDoc.getString("status")
                );
                eventsList.add(event);
            }
        }

        return eventsList;
    }
//    public static List<Events> GetAllinscriptions() {
//        List<String , String> eventsList = new ArrayList<>();
//
//
//
//        MongoCollection<Document> inscriptionCollection = GetConnection(.getCollection("Inscriptions");
//
//
//
//        return eventsList;
//    }
    
    public static void InscriptionCancel(String eventId,String userid){


        MongoCollection<Document> inscriptionCollection = GetConnection().getCollection("Inscriptions");
        System.out.println(eventId +"" +userid);

        Document filter = new Document("eventID", eventId)
                .append("userID", userid);

        DeleteResult x = inscriptionCollection.deleteOne(filter);
        if(x.getDeletedCount() == 1){

            System.out.println("Inscription canceled for event ID: " + eventId);

        }else
            System.out.println("wasnt deleted");


    }
    public static void Delete(String id){


        MongoCollection<Document> eventsCollection = GetConnection().getCollection("events");

        Document filter = new Document("id", id);

        DeleteResult result = eventsCollection.deleteOne(filter);

        if (result.getDeletedCount() > 0) {
            System.out.println("Event with ID " + id + " has been deleted.");
        } else {
            System.out.println("No event found with ID " + id + ".");
        }


    }

    private  static MongoDatabase GetConnection(){
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db");
        return mongoClient.getDatabase("events_db");

    }
    // Getters and setters
    public String getId() {
        return id;
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


}
