package org.example.Database.Models;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.example.Database.Controllers.AuthController;
import org.example.Utils.DbConnection;
import org.example.Utils.HandleErrors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Users {
    public static String Role;
    public  String password;
    public  String email;
    public Users(String email,String password){
        this.password = password;
        this.email = email;
    }
    public static void register(String email, String password) {
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db");
        MongoCollection<Document> usersCollection = database.getCollection("users");
        Document existingUser = usersCollection.find(new Document("email", email)).first();
        if (existingUser != null) {
            HandleErrors.DisplayError("A user with this email already exists.");
            HandleErrors.Status = false;
        return;
        }
        long userCount = usersCollection.countDocuments();

        String role;
        if (userCount == 0) {
            role = "admin";
        } else {
            role = "user";
        }

        Document newUser = new Document("id", UUID.randomUUID().toString())
                .append("email", email)
                .append("password", password)
                .append("role", role);


        usersCollection.insertOne(newUser);

        System.out.println("User registered with role: " + role);
    }

    public static boolean login(String email, String password) {
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db");
        MongoCollection<Document> usersCollection = database.getCollection("users");

        Document userDoc = usersCollection.find(new Document("email", email)).first();


        if (userDoc != null) {
            String hashedPassword = userDoc.getString("password");
            Role = userDoc.getString("role");

            if(password.equals(hashedPassword)){
                HandleErrors.DisplayError("Login successful.");

                AuthController.userid = userDoc.getString("id");

                return true;
            }

        }
        HandleErrors.DisplayError("Email or password does not match.");
        HandleErrors.Status = false;
        return false;
    }
    public static void modifyUserProfile(String newPassword) {
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db");
        MongoCollection<Document> userCollection = database.getCollection("users");

        Document filter = new Document("id", AuthController.userid);
        Document update = new Document("$set", new Document("password", newPassword));


        UpdateResult result = userCollection.updateOne(filter, update);


        if (result.getMatchedCount() > 0) {
            System.out.println("Password has been updated.");
        } else {
            System.out.println("No matching user found. Password was not updated.");
        }
    }

    public static void GetStats() {
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db");
        MongoCollection<Document> usersCollection = database.getCollection("users");

        long totalUsers = usersCollection.countDocuments();

        System.out.println("User Statistics:");
        System.out.println("----------------");
        System.out.println("Total number of users: " + totalUsers);

        Document roleGroup = new Document("$group", new Document("_id", "$role").append("count", new Document("$sum", 1)));
        AggregateIterable<Document> roleStats = usersCollection.aggregate(Collections.singletonList(roleGroup));

        System.out.println("\nUsers by role:");
        for (Document doc : roleStats) {
            System.out.println("Role: " + doc.getString("_id") + " - Count: " + doc.getInteger("count"));
        }

        Document statusGroup = new Document("$group", new Document("_id", "$status").append("count", new Document("$sum", 1)));
        AggregateIterable<Document> statusStats = usersCollection.aggregate(Collections.singletonList(statusGroup));

        System.out.println("\nUsers by status:");
        for (Document doc : statusStats) {
            System.out.println("Status: " + "active" + " - Count: " + doc.getInteger("count"));
        }

        Document countryGroup = new Document("$group", new Document("_id", "$country").append("count", new Document("$sum", 1)));
        AggregateIterable<Document> countryStats = usersCollection.aggregate(Collections.singletonList(countryGroup));

        System.out.println("\nUsers by country:");
        for (Document doc : countryStats) {
            System.out.println("Country: " + "morocco" + " - Count: " + doc.getInteger("count"));
        }

    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static void Delete(String email){
        MongoClient mongoClient = DbConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("events_db");
        MongoCollection<Document> eventsCollection = database.getCollection("users");

        Document filter = new Document("email", email);

        DeleteResult result = eventsCollection.deleteOne(filter);

        if (result.getDeletedCount() > 0) {
            System.out.println("User with email " + email + " has been deleted.");
        } else {
            System.out.println("No event found with ID " + email + ".");
        }


    }

    public static List<Users> fetchUsers() {
        try{
            List<Users> fetchedUsers = new ArrayList<>();

            MongoClient mongoClient = DbConnection.getMongoClient();
            MongoDatabase database = mongoClient.getDatabase("events_db");
            MongoCollection<Document> usersCollection = database.getCollection("users");

            for (Document doc : usersCollection.find()) {

                Users user = new Users(
                        doc.getString("email"),
                        doc.getString("password")
                );
                fetchedUsers.add(user);
            }


            return fetchedUsers;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }

}
