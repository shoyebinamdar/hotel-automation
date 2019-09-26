package com.sahaj.hotelautomation.corridors;

import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import lombok.Builder;
import com.sahaj.hotelautomation.utils.State;

@Builder
public class SubCorridor implements Corridor {
    private ElectronicEquipment light, airConditioner;
    private boolean hasMovement;

    @Override
    public int getConsumption() {
        return light.getConsumption() + airConditioner.getConsumption();
    }

    public void movementDetected() {
        this.hasMovement = true;
        this.light.on();
    }
    
    public void noMovementDetected() {
        this.hasMovement = false;
        this.light.off();
        this.airConditioner.on();
    }

    public void turnOffAC() {
        this.airConditioner.off();
    }

    public State getACState() {
        return this.airConditioner.getState();
    }

    @Override
    public void printStatus() {
        System.out.println("Light : " + light.getState() + " | AC : " + airConditioner.getState());
    }
}
