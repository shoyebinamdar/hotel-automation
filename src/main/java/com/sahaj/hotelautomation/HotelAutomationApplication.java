package com.sahaj.hotelautomation;

import com.sahaj.hotelautomation.entities.Hotel;
import com.sahaj.hotelautomation.entities.corridors.Corridor;
import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.entities.sensors.MotionSensor;
import com.sahaj.hotelautomation.entities.sensors.Sensor;
import com.sahaj.hotelautomation.output.ConsoleOutputListener;
import com.sahaj.hotelautomation.output.OutputListener;
import com.sahaj.hotelautomation.services.MovementService;
import com.sahaj.hotelautomation.utils.HotelFactory;

import java.util.Scanner;

public class HotelAutomationApplication {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        OutputListener outputListener = new ConsoleOutputListener();

        System.out.println("Number of floors");
        Integer numberOfFloors = Integer.parseInt(in.nextLine());

        System.out.println("Main corridors per floor");
        Integer numberOfMainCorridors = Integer.parseInt(in.nextLine());

        System.out.println("Sub corridors per floor");
        Integer numberOfSubCorridors = Integer.parseInt(in.nextLine());

        Hotel hotel = HotelFactory.create(numberOfFloors, numberOfMainCorridors, numberOfSubCorridors);

        System.out.println("Default state");
        outputListener.write(hotel);

        while (true) {
            System.out.println("Trigger sensor ...");
            System.out.println("Enter floor number");
            Integer floorIndex = Integer.parseInt(in.nextLine());
            System.out.println("Enter sub corridor number");
            Integer subCorridorIndex = Integer.parseInt(in.nextLine());

            Floor floor = hotel.getFloors().get(floorIndex - 1);
            Corridor corridor = floor.getSubCorridors().get(subCorridorIndex - 1);
            Sensor sensor = new MotionSensor();
            MovementService movementService = new MovementService();

            floor.registerCorridors();
            sensor.registerCorridor(corridor);

            movementService.triggerMovement(sensor);

            outputListener.write(hotel);

            Thread.sleep(6000);

            movementService.triggerStagnation(sensor);
            outputListener.write(hotel);
        }
    }
}
