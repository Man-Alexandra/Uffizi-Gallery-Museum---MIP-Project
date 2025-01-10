package models;

import java.time.LocalTime;
import java.util.Scanner;

public class Museum {



    // Attributes
    private String nameOfMuseum;
    private String location;
    private int numberOfVisitors;
    private MuseumSchedule museumSchedule;



    //Constructor
    public Museum(String nameOfMuseum, String location, int numberOfVisitors) {
        this.nameOfMuseum = nameOfMuseum;
        this.location = location;
        this.numberOfVisitors = numberOfVisitors;
        this.museumSchedule = new MuseumSchedule();

    }



    //Getter & Setter
    public String getNameOfMuseum() {
        return nameOfMuseum;
    }
    public void setNameOfMuseum(String nameOfMuseum) {
        this.nameOfMuseum = nameOfMuseum;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }
    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }
    public MuseumSchedule getMuseumSchedule() {
        return museumSchedule;
    }
    public void setMuseumSchedule(MuseumSchedule museumSchedule) {
        this.museumSchedule = museumSchedule;
    }



    //Methods
    public String getMuseumDetails(){
        return nameOfMuseum + "\t" + location  + "\n";
    }
    public String getMuseumScheduleString() {
        StringBuilder scheduleString = new StringBuilder();
        for (MuseumSchedule.Day day : MuseumSchedule.Day.values()) {
            scheduleString.append(day).append(": ")
                    .append(museumSchedule.getHours(day)).append("\n");
        }
        return scheduleString.toString();
    }

}
