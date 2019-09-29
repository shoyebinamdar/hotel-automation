package com.sahaj.hotelautomation.entities.sensors;

import com.sahaj.hotelautomation.entities.corridors.Corridor;

public interface Sensor {
    void movementStarted() throws Exception;
    void movementStopped() throws Exception;
    void registerCorridor(Corridor corridor);
    Corridor getCorridor();
}
