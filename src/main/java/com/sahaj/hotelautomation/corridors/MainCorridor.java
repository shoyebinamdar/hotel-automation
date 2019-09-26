package com.sahaj.hotelautomation.corridors;

import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import lombok.Builder;

@Builder
public class MainCorridor implements Corridor{
    private ElectronicEquipment light, airConditioner;

    public int getConsumption() {
        return light.getConsumption() + airConditioner.getConsumption();
    }

    @Override
    public void printStatus() {
        System.out.println("Light : " + light.getState() + " | AC : " + airConditioner.getState());
    }
}
