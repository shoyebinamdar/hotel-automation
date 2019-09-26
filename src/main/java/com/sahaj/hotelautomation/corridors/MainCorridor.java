package com.sahaj.hotelautomation.corridors;

import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import lombok.Builder;

import java.util.List;

@Builder
public class MainCorridor implements Corridor{
    private List<ElectronicEquipment> equipments;

    public int getConsumption() {
        return equipments.stream()
                .map(electronicEquipment -> electronicEquipment.getConsumption())
                .reduce(0, Integer::sum);
    }

    public List<ElectronicEquipment> getEquipments() {
        return equipments;
    }
}
