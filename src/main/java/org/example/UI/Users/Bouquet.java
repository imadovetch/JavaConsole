package org.example.UI.Users;

import org.example.Database.Controllers.Users.GetEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bouquet {
    private String components = "" ;
    public Bouquet() {
        GetEvents getEvents = new GetEvents();
        List<Map<String, Object>> events = getEvents.fetchData();

        StringBuilder names = new StringBuilder();
        names.append(this.Stringformater("Name"));
        StringBuilder locations = new StringBuilder();
        locations.append(this.Stringformater("location"));
        StringBuilder statuses = new StringBuilder();
        statuses.append(this.Stringformater("status"));
        StringBuilder places = new StringBuilder();
        places.append(this.Stringformater("place"));
        StringBuilder ids = new StringBuilder();
        ids.append(this.Stringformater("id"));
        StringBuilder photos = new StringBuilder();
        photos.append(this.Stringformater("photo"));

        int columns = 4;
        int index = 0;

        for (Map<String, Object> event : events) {
            String name ="";
            String location="" ;
            String status ="";
            String place ="";
            String id ="";
            String photo ="";

            // Extract event details
            for (Map.Entry<String, Object> entry : event.entrySet()) {

                switch (entry.getKey()) {
                    case "name":

                        name = Stringformater(" : " + entry.getValue());;
                        break;
                    case "location":
                        location = Stringformater(" : " + entry.getValue());;
                        break;
                    case "status":
                        status = Stringformater(" : " + entry.getValue());;
                        break;
                    case "places":
                        place = Stringformater(" : " + entry.getValue());;
                        break;
                    case "id":
                        id = Stringformater(" : " + entry.getValue());;
                        break;
                    case "photo":
                        String path = (String) entry.getValue();
                        String fileUriString = path.replace("\\", "/");
                        String url = "file:///" + fileUriString;
//                        System.out.println("jknjwfo" + url);
                        photo = Stringformater(" : " + url);
                        break;
                }
            }

            // Append details to the respective columns
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


        components = "";
        components += names.toString() + "\n";
        components += locations.toString() + "\n";
        components += statuses.toString() + "\n";
        components += places.toString() + "\n";
        components += ids.toString() + "\n";
        components += photos.toString() + "\n";

        // Print the grid to the console
        System.out.println(components);
    }

    private String Stringformater(String x) {
        // Ensure x is not null
        if (x == null) {
            return "   null   ";
        }

        // Define the maximum length allowed before truncation
        int maxLength = 19;

        if (x.length() > maxLength) {
            x = x.substring(0, maxLength - 2) + "..";
        }

        int totalLength = 21;


        if (x.length() > maxLength) {
            x = x.substring(0, maxLength - 2) + "..";
        }

        int spacesToAdd = totalLength - x.length();
        int leftSpaces = spacesToAdd / 2;
        int rightSpaces = spacesToAdd - leftSpaces;


        return " ".repeat(leftSpaces) + x + " ".repeat(rightSpaces);
    }



}
