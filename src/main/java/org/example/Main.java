package org.example;

import org.example.controller.RoomController;
import org.example.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        User user = new User("István");
        RoomController roomController = new RoomController(user);

        List<LocalDate> roomDate = new ArrayList<>();
        roomDate.add(LocalDate.parse("2023-10-26"));
        roomDate.add(LocalDate.parse("2023-10-27"));
        roomDate.add(LocalDate.parse("2023-10-28"));
        roomDate.add(LocalDate.parse("2023-10-29"));
        roomController.addRoom("VIP", 20, roomDate);


//        roomController.listAvailableRooms();

        while (true) {
            System.out.println("1. User command list.");
            System.out.println("2. Admin command list.");
            System.out.println("Enter the correct number");
            int choice = scan.nextInt();


            switch (choice) {
                case (1):
                    System.out.println("1. User booking list.");
                    System.out.println("2. Available room list.");
                    System.out.println("3. Book a room by room name");
                    System.out.println("4. Cancel the room reservation by room name");
                    System.out.println("Enter the correct number");
                    int userchoice = scan.nextInt();
                    switch (userchoice) {
                        case (1):
                            user.listingUserRoomList();
                            break;
                        case (2):
                            roomController.listAvailableRooms();
                            break;
                        case(3):
                            System.out.println("Enter the name of the selected room: ");
                            String roomName = scan.next();
                            System.out.println("Enter the renting begin date: (please in this format: yyyy-mm-dd");
                            LocalDate beginDate = LocalDate.parse(scan.next());
                            System.out.println("How many days do you want to rent:");
                            int rentDays = scan.nextInt();
                            roomController.rentRoom(user,roomName,beginDate,rentDays);
                            break;
                        case(4):
                            System.out.println("Enter the name of the selected room: ");
                            String deleteRoomName = scan.next();
                            roomController.deleteBooking(user, deleteRoomName);
                            break;
                        default:
                            System.out.println("Wrong command number");
                    }

                    break;
                case (2):
                    System.out.println("1. Add available room.");
                    System.out.println("2. Add available date to the room");
                    int adminchoice = scan.nextInt();
                    switch (adminchoice) {
                        case (1):
                            System.out.println("Enter the new room name ");
                            String newRoomName = scan.next();
                            System.out.println("Enter the price per day ");
                            int pricePerDays = scan.nextInt();
                            List<LocalDate> newRoomAvailableDates = new ArrayList<>();
                            System.out.println(" How many available dates do yozu want add the new room?");
                            int availableDateNumber = scan.nextInt();
                            for (int i = 0; i <availableDateNumber ; i++) {
                                System.out.println("Enter the available date: (please in this format: yyyy-mm-dd");
                                LocalDate date = LocalDate.parse(scan.next());
                                newRoomAvailableDates.add(date);
                            }
                            roomController.addRoom(newRoomName,pricePerDays, newRoomAvailableDates);
                            break;
                        case(2):
                            System.out.println("Enter the room name ");
                            String roomName = scan.next();
                            System.out.println("Enter the available date: (please in this format: yyyy-mm-dd");
                            LocalDate date = LocalDate.parse(scan.next());
                            roomController.addRoomAvailableDateByName(roomName,date);

                        default:
                            System.out.println("Wrong command number");
                    }

                    break;


                case (3):
                    System.exit(1);

                default:
                    System.out.println("Wrong command number");
            }


        }


    }
}