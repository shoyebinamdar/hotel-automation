package com.hotel.corridors;

import com.hotel.equipments.ElectronicEquipment;
import lombok.Builder;

@Builder
public class MainCorridor implements Corridor{
    private ElectronicEquipment light, airConditioner;

    public int getConsumption() {
        return light.getConsumption() + airConditioner.getConsumption();
    }
}
