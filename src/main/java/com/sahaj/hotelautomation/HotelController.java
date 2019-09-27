package com.sahaj.hotelautomation;

import com.sahaj.hotelautomation.entities.Hotel;

public class HotelController {
    private Hotel hotel;

    public HotelController(Hotel hotel) {
        this.hotel = hotel;
    }

    public int consumption() {
        return hotel.getFloors().stream().map(f -> f.consumption()).reduce(0, Integer::sum);
    }
}
