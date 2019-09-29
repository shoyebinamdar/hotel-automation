package com.sahaj.hotelautomation.services;

import com.sahaj.hotelautomation.entities.sensors.Sensor;

public class MovementService {
    public void triggerMovement(Sensor sensor) throws Exception {
        sensor.movementStarted();
    }

    public void triggerStagnation(Sensor sensor) throws Exception {
        sensor.movementStopped();
    }
}
