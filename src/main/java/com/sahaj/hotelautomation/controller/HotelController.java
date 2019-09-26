package com.sahaj.hotelautomation.controller;

import com.sahaj.hotelautomation.output.OutputListener;

public class HotelController implements ControllerInterface {
    private Hotel hotel;

    public HotelController(Hotel hotel) {
        this.hotel = hotel;
    }

    public int consumption() {
        return hotel.getFloors().stream().map(f -> f.consumption()).reduce(0, Integer::sum);
    }

    public void write(OutputListener outputListener) {
        outputListener.write(hotel);
    }

    @Override
    public void update(Floor floor) {
        hotel.getFloors().stream().filter(c -> c.equals(floor)).findFirst().ifPresent(Floor::consumption);
    }

    public void addNotifiers() {
        hotel.getFloors().forEach(floor -> floor.attachObserver(this));
    }
}
