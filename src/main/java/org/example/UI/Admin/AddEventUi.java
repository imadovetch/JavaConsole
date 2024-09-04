package org.example.UI.Admin;

import org.example.Database.Controllers.Admin.AddEventcot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddEventUi {

    private Scanner scanner;
    private AddEventcot controller;

    public AddEventUi() {
        this.scanner = new Scanner(System.in);
        this.controller = new AddEventcot();  // Initialize the controller here
    }

    public void addEvent() {
        Map<String, Object> event = new HashMap<>();

        String name = getName();
        event.put("name", name);

        String location = getLocation();
        event.put("location", location);

        int places = getPlaces();
        event.put("places", places);

        String photo = getDate();
        event.put("photo", photo);

        String status = getStatus();
        event.put("status", status);


        controller.addEventToDatabase(event);
    }

    private String getName() {
        while (true) {
            System.out.println("Enter event name:");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Event name cannot be empty! Please try again.");
            }
        }
    }
    private String getDate() {
        Scanner scanner = new Scanner(System.in);
        String datePattern = "\\d{4}/\\d{2}/\\d{2}";

        while (true) {
            System.out.println("Enter event date (format: yyyy/mm/dd):");
            String date = scanner.nextLine();

            if (date.matches(datePattern)) {
                return date;
            } else {
                System.out.println("Invalid date format! Please enter the date in the format yyyy/mm/dd.");
            }
        }
    }

    private String getLocation() {
        while (true) {
            System.out.println("Enter event location:");
            String location = scanner.nextLine();
            if (!location.isEmpty()) {
                return location;
            } else {
                System.out.println("Event location cannot be empty! Please try again.");
            }
        }
    }

    private int getPlaces() {
        while (true) {
            System.out.println("Enter number of places:");
            try {
                int places = Integer.parseInt(scanner.nextLine());
                if (places > 0) {
                    return places;
                } else {
                    System.out.println("Number of places must be greater than 0! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of places! Please enter a valid integer.");
            }
        }
    }

    private String uploadPhoto() {
        return "pathd";
//                JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Upload Photo");
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif"));
//
//        int result = fileChooser.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            if (selectedFile.exists()) {
//                System.out.println("Photo selected: " + selectedFile.getAbsolutePath());
//
//                // Prompt for destination directory
//                System.out.println("Enter the destination directory where you want to upload the photo:");
//                String destinationDirectory = scanner.nextLine();
//                File destinationDir = new File(destinationDirectory);
//
//                // Validate the destination directory
//                if (!destinationDir.exists() || !destinationDir.isDirectory()) {
//                    System.out.println("The specified directory does not exist. Please provide a valid directory.");
//                    return uploadPhoto(); // Ask to upload the photo again
//                }
//
//                // Construct the destination path
//                String destinationPath = Paths.get(destinationDirectory, selectedFile.getName()).toString();
//
//                try {
//                    // Copy the file to the destination directory
//                    Files.copy(selectedFile.toPath(), Paths.get(destinationPath));
//                    System.out.println("Photo uploaded successfully to " + destinationPath);
//                    return destinationPath;
//                } catch (IOException e) {
//                    System.out.println("An error occurred while uploading the photo: " + e.getMessage());
//                    return null;
//                }
//            } else {
//                System.out.println("The selected file does not exist. Please select a valid file.");
//                return uploadPhoto(); // Ask to upload the photo again
//            }
//        } else {
//            System.out.println("No file selected.");
//            return null;
//        }
    }

    private String getStatus() {
        while (true) {
            System.out.println("Enter event status:");
            String status = scanner.nextLine();
            if (!status.isEmpty() && status.length() <= 50) {
                return status;
            } else {
                System.out.println("Event status cannot be empty or exceed 50 characters! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        AddEventUi addEventUi = new AddEventUi();
        addEventUi.addEvent();
    }
}
