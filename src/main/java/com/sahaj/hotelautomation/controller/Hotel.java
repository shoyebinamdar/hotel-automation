package com.sahaj.hotelautomation.controller;

import lombok.Builder;

import java.util.List;

@Builder
public class Hotel {
    private List<Floor> floors;

    public List<Floor> getFloors() {
        return floors;
    }
}
