package com.sahaj.hotelautomation.services;

import com.sahaj.hotelautomation.entities.corridors.Corridor;
import com.sahaj.hotelautomation.entities.floors.Floor;

public class MovementService {
    public void triggerMovement(Floor floor, Corridor corridor) throws Exception {
        floor.movementDetected(corridor);
    }

    public void triggerStagnation(Floor floor) {
        floor.noMovementDetected();
    }
}
