package models;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class MuseumSchedule {



    // Attributes
    private Map<Day, OpeningHours> schedule;

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }



    // Constructor
    public MuseumSchedule() {
        this.schedule = new HashMap<>();
        setDefaultSchedule();
    }



    private void setDefaultSchedule() {
        schedule.put(Day.MONDAY, new OpeningHours(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        schedule.put(Day.TUESDAY, new OpeningHours(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        schedule.put(Day.WEDNESDAY, new OpeningHours(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        schedule.put(Day.THURSDAY, new OpeningHours(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        schedule.put(Day.FRIDAY, new OpeningHours(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        schedule.put(Day.SATURDAY, new OpeningHours(LocalTime.of(9, 0), LocalTime.of(18, 0)));
        schedule.put(Day.SUNDAY, new OpeningHours(LocalTime.of(10, 0), LocalTime.of(16, 0)));
    }




    // Getters and Setters
    public void setHours(Day day, LocalTime openingTime, LocalTime closingTime) {
        schedule.put(day, new OpeningHours(openingTime, closingTime));
    }
    public OpeningHours getHours(Day day) {
        return schedule.get(day);
    }
    public boolean isOpen(Day day, LocalTime currentTime) {
        OpeningHours hours = schedule.get(day);
        if (hours == null)
            return false;
        return !currentTime.isBefore(hours.getOpeningTime()) && !currentTime.isAfter(hours.getClosingTime());
    }


}
