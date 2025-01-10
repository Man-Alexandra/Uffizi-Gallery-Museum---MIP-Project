package models;

import java.time.LocalTime;

public class OpeningHours {



    // Attributes
    private LocalTime openingTime;
    private LocalTime closingTime;



    // Constructor
    public OpeningHours(LocalTime openingTime, LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }



    // Getters and Setters
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    @Override
    public String toString() {
        return "Opening: " + openingTime + ", Closing: " + closingTime;
    }
}
