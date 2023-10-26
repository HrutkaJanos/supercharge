package org.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Room {

    private String name;
    private String city;
    private List<LocalDate> dates;
    private int pricePerDay;

    public Room(String name, int pricePerDay, List<LocalDate> date) {
        this.name=name;
        this.city = "Budapest";
        this.dates = date;
        this.pricePerDay = pricePerDay;
    }



}
