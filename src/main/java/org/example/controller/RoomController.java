package org.example.controller;

import lombok.Data;
import org.example.entity.Room;
import org.example.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoomController {

    private List<Room> rooms;
    private User user;
    private LocalDate todaysDate;

    public RoomController(User user) {
        this.rooms = new ArrayList<>();
        this.user = user;
        this.todaysDate = LocalDate.now();
    }

    public void listAvailableRooms() {
        for (Room room : rooms) {
            System.out.println("Room name: " + room.getName());
            System.out.println("Room price oper days: " + room.getPricePerDay()+ " euro.");
            System.out.println("Available dates: ");
            for (LocalDate date : room.getDates()) {
                if (date.isEqual(todaysDate) || date.isAfter(todaysDate)) {
                    System.out.println(date);
                }
            }
        }
    }

    public void addRoom(String name, int pricePerDay, List<LocalDate> dates) {
        if (roomNameCheck(name)) {
            Room room = new Room(name, pricePerDay, dates);
            rooms.add(room);
            System.out.println("Room added");
        } else {
            System.out.println("Room name already exists.");
        }
    }

    public boolean roomNameCheck(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public void addRoomAvailableDateByName(String name, LocalDate date) {
        if (!roomNameCheck(name)) {
            for (Room room : rooms) {
                if (room.getName().equals(name)) {
                    room.getDates().add(date);
                    System.out.println("Date added to room");
                }
            }
        } else {
            System.out.println("There is no room with that name");
        }
    }

    public void rentRoom(User user, String roomName, LocalDate beginDate, int daysNumber) {
        if (!roomNameCheck(roomName)) {
            if (roomRentAvailableDateByDaysNumber(roomName, beginDate, daysNumber)) {
                for (Room room : rooms) {
                    if (room.getName().equals(roomName)) {
                        user.getRoomList().add(new Room(room.getName(), room.getPricePerDay(), userRentingDates(beginDate, daysNumber)));
                        List<LocalDate> rentindDaysDateList = userRentingDates(beginDate, daysNumber);
                        for (int i = 0; i < rentindDaysDateList.size(); i++) {
                            room.getDates().remove(rentindDaysDateList.get(i));
                        }
                        System.out.println("Booking approved");
                    }
                }
            }
        }
    }

    public boolean roomRentAvailableDateByDaysNumber(String roomName, LocalDate beginDate, int daysNumber) {
        if (!roomNameCheck(roomName)) {
            for (Room room : rooms) {
                if (room.getName().equals(roomName)) {
                    for (int j = 0; j < daysNumber; j++) {
                        LocalDate searchDate = beginDate.plusDays(j);
                        for (int i = 0; i < room.getDates().size(); i++) {
                            if (!room.getDates().contains(searchDate)) {
                                System.out.println("This day cannot be booked: " + searchDate);
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        System.out.println("There is no room with that name");
        return false;
    }

    public List<LocalDate> userRentingDates(LocalDate beginDate, int rentDays) {
        List<LocalDate> retingDates = new ArrayList<>();
        for (int i = 0; i < rentDays; i++) {
            retingDates.add(beginDate.plusDays(i));
        }
        return retingDates;
    }

    public void deleteBooking(User user, String roomName) {
        if (checkingUserRoomByName(user, roomName)) {
            List<LocalDate> roomsDateList = new ArrayList<>();
            if (deleteRoomByDate(user, roomName)) {
                for (int i = 0; i < user.getRoomList().size(); i++) {
                    if (user.getRoomList().get(i).getName().equals(roomName)) {
                        roomsDateList = user.getRoomList().get(i).getDates();
                        user.getRoomList().remove(i);
                    }
                }
                for (int i = 0; i < roomsDateList.size(); i++) {
                    addRoomAvailableDateByName(roomName, roomsDateList.get(i));
                }
            }
        }
    }

    public boolean deleteRoomByDate(User user, String roomName) {
        for (Room room : user.getRoomList()) {
            if (room.getName().equals(roomName)) {
                for (LocalDate date : room.getDates()) {
                    if (date.isBefore(LocalDate.now())) {
                        System.out.println("The room can no longer be canceled!");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkingUserRoomByName(User user, String roomName) {
        for (Room room : user.getRoomList()) {
            if (room.getName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }


}
