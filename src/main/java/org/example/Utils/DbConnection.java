package org.example.Utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DbConnection {
    private static MongoClient mongoClient;
    private static final String CONNECTION_STRING = "mongodb+srv://ibouderoua63:QBwjZ1zUXho1Yd0M@cluster0.hriid.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            synchronized (DbConnection.class) {
                if (mongoClient == null) {
                    try {
                        mongoClient = MongoClients.create(CONNECTION_STRING);
                        System.out.println("Connected to MongoDB successfully!");
                    } catch (Exception e) {
                        System.err.println("Failed to connect to MongoDB");
//                        e.printStackTrace();
                    }
                }
            }
        }
        return mongoClient;
    }
}
