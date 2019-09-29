package com.sahaj.hotelautomation.entities.corridors;

import com.sahaj.hotelautomation.entities.floors.Floor;

public interface Corridor {
    int getConsumption();

    void onActivity() throws Exception;

    void onInactivity() throws Exception;

    void register(Floor floor);

    void notifyFloor() throws Exception;

    void reset();
}
