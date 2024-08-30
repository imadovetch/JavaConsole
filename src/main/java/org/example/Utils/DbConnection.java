package org.example.Utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientException;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
public class DbConnection {
    private static volatile MongoClient mongoClient;
    private static final String CONNECTION_STRING = "mongodb+srv://ibouderoua63:QBwjZ1zUXho1Yd0M@cluster0.hriid.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "admin";

    private DbConnection() {
        // Private constructor to prevent instantiation
    }

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            synchronized (DbConnection.class) {
                if (mongoClient == null) {
                    try {
                        ServerApi serverApi = ServerApi.builder()
                                .version(ServerApiVersion.V1)
                                .build();
                        MongoClientSettings settings = MongoClientSettings.builder()
                                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                                .serverApi(serverApi)
                                .build();
                        mongoClient = MongoClients.create(settings);
                        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                        database.runCommand(new Document("ping", 1));
                        System.out.println("Connected to MongoDB successfully!");
                    } catch (MongoException e) {
                        System.err.println("Error connecting to MongoDB: " + e.getMessage());
                    }
                }
            }
        }
        return mongoClient;
    }
}

