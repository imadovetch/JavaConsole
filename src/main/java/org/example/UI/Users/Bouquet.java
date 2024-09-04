package org.example.UI.Users;

import org.example.Database.Controllers.Users.GetEvents;
import org.example.Database.Models.Events;

import java.util.List;

public class Bouquet {
    private String components = "";
    private static Bouquet instance;

    public static Bouquet getInstance() {
        if (instance == null) {
            instance = new Bouquet();
        }
        return instance;
    }

    public void DisplayBuquet() {

        List<Events> events = Events.fetcheventsfroDb();
        System.out.println(events.toString());
        StringBuilder names = new StringBuilder();
        names.append(this.Stringformater("Name")).append('|');
        StringBuilder locations = new StringBuilder();
        locations.append(this.Stringformater("Location")).append('|');
        StringBuilder statuses = new StringBuilder();
        statuses.append(this.Stringformater("Status")).append('|');
        StringBuilder places = new StringBuilder();
        places.append(this.Stringformater("Places")).append('|');
        StringBuilder ids = new StringBuilder();
        ids.append(this.Stringformater("ID")).append('|');
        StringBuilder photos = new StringBuilder();
        photos.append(this.Stringformater("Photo")).append('|');

        int columns = 10;
        int index = 0;

        for (Events event : events) {

            String name = Stringformater(" : " + event.getName());
            String location = Stringformater(" : " + event.getLocation());
            String status = Stringformater(" : " + event.getStatus());
            String place = Stringformater(" : " + event.getPlaces());
            String id = Stringformater(" : " + event.getId());
            String photo = "";

            String path = event.getPhoto();
            if (path != null) {
                String fileUriString = path.replace("\\", "/");
                String url = "" + fileUriString;
                photo = Stringformater(" : " + url);
            }

            names.append(String.format("%-20s", name)).append(" | ");
            locations.append(String.format("%-20s", location)).append(" | ");
            statuses.append(String.format("%-20s", status)).append(" | ");
            places.append(String.format("%-20s", place)).append(" | ");
            ids.append(String.format("%-20s", id)).append(" | ");
            photos.append(photo).append(" | ");

            index++;
            if (index % columns == 0) {
                names.append("\n");
                locations.append("\n");
                statuses.append("\n");
                places.append("\n");
                ids.append("\n");
                photos.append("\n");
            }
        }

        components = names.toString() + "\n" +
                locations.toString() + "\n" +
                statuses.toString() + "\n" +
                places.toString() + "\n" +
                ids.toString() + "\n" +
                photos.toString() + "\n";

        System.out.println( components );
    }

    private String Stringformater(String x) {
        if (x == null) {
            return "   null   ";
        }

        int maxLength = 52;

        if (x.length() > maxLength) {
            x = x.substring(0, maxLength - 2) + "..";
        }

        int totalLength = 55;

        int spacesToAdd = totalLength - x.length();
        int leftSpaces = spacesToAdd / 2;
        int rightSpaces = spacesToAdd - leftSpaces;

        return " ".repeat(leftSpaces) + x + " ".repeat(rightSpaces);
    }
}
