package com.sahaj.hotelautomation.controller;

import java.util.List;

public class HotelController implements ControllerInterface {
    private Hotel hotel;

    public HotelController(Hotel hotel) {
        this.hotel = hotel;
    }

    public int consumption() {
        return hotel.getFloors().stream().map(f -> f.consumption()).reduce(0, Integer::sum);
    }

    public void printStatus() {
        List<Floor> floors = hotel.getFloors();
        for (int i = 0 ; i < floors.size(); i++) {
            System.out.println("Floor " + (i + 1));
            floors.get(i).printStatus();
        }
    }

    @Override
    public void update(Floor floor) {
        hotel.getFloors().stream().filter(c -> c.equals(floor)).findFirst().ifPresent(Floor::consumption);
    }

    public void addNotifiers() {
        hotel.getFloors().stream().forEach(floor -> floor.attachObserver(this));
    }
}
