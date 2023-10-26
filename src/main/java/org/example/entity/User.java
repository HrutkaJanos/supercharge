package org.example.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private String name;
    private List<Room> roomList;

    public User(String name) {
        this.name=name;
        this.roomList =new ArrayList<>();
    }

    public void listingUserRoomList(){
        for (Room room : roomList) {
            System.out.println("Room name :"+room.getName());
            System.out.println("Booking begin date :"+room.getDates().get(0));
            System.out.println("Period of time : " + room.getDates().size());
            System.out.println("Full price of room reservation : " + room.getDates().size()*room.getPricePerDay());
            System.out.println();
        }
    }


}
