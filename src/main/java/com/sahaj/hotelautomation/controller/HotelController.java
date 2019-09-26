package com.sahaj.hotelautomation.controller;

public class HotelController implements ObserverInterface {
    private Hotel hotel;

    public HotelController(Hotel hotel) {
        this.hotel = hotel;
    }

    public int consumption() {
        return hotel.getFloors().stream().map(f -> f.consumption()).reduce(0, Integer::sum);
    }

    @Override
    public void update(Floor floor) {
        hotel.getFloors().stream().filter(c -> c.equals(floor)).findFirst().ifPresent(Floor::consumption);
    }

    public void addNotifiers() {
        hotel.getFloors().stream().forEach(floor -> floor.attachObserver(this));
    }
}
