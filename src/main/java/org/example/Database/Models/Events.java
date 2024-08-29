package org.example.Database.Models;

public class Events {
    private int id;
    private String name;
    private String location;
    private int places;
    private String photo;

    // Constructor
    public Events(int id, String name, String location, int places, String photo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.places = places;
        this.photo = photo;
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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", places=" + places +
                ", photo='" + photo + '\'' +
                '}';
    }
    public void SaveEvent(){

    }
    public String FetchEvents(){
        return null;
    }
}

