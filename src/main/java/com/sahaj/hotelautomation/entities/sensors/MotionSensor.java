package com.sahaj.hotelautomation.entities.sensors;

import com.sahaj.hotelautomation.entities.corridors.Corridor;

public class MotionSensor implements Sensor {
    private Corridor corridor;

    public void movementStarted() throws Exception {
        this.corridor.onActivity();
    }

    public void movementStopped() throws Exception {
        this.corridor.onInactivity();
    }

    public void registerCorridor(Corridor corridor) {
        this.corridor = corridor;
    }
}
