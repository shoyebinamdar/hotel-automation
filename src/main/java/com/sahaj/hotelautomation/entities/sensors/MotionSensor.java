package com.sahaj.hotelautomation.entities.sensors;

import com.sahaj.hotelautomation.entities.corridors.Corridor;

public class MotionSensor implements Sensor {
    private boolean hasMovement = false;
    private Corridor corridor;

    public void movementStarted() throws Exception {
        this.hasMovement = true;
        this.corridor.onActivity();
    }

    public void movementStopped() throws Exception {
        this.hasMovement = false;
        this.corridor.onInactivity();
    }

    public void registerCorridor(Corridor corridor) {
        this.corridor = corridor;
    }

    public Corridor getCorridor() {
        return corridor;
    }
}
