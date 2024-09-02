package org.example.Database.Models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.example.Database.Controllers.AuthController;
import org.example.Utils.DbConnection;
import org.example.Utils.HandleErrors;

import java.util.UUID;

public class Users {
    public static String Role;
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

}
