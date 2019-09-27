package com.sahaj.hotelautomation.entities.corridors;

import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.utils.EquipmentType;
import lombok.Builder;

import java.util.List;

@Builder
public class SubCorridor implements Corridor {
    private List<ElectronicEquipment> equipments;
    private boolean hasMovement;

    @Override
    public int getConsumption() {
        return equipments.stream()
                .map(electronicEquipment -> electronicEquipment.getConsumption())
                .reduce(0, Integer::sum);
    }

    public void movementDetected() {
        this.hasMovement = true;
        equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.LIGHT)
                .findFirst()
                .ifPresent(ElectronicEquipment::on);
    }
    
    public void noMovementDetected() {
        this.hasMovement = false;
        equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.LIGHT)
                .findFirst()
                .ifPresent(ElectronicEquipment::off);
        equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.AC)
                .findFirst()
                .ifPresent(ElectronicEquipment::on);
    }

    public void turnOffAC() {
        equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.AC)
                .findFirst()
                .ifPresent(ElectronicEquipment::off);
    }

    public boolean hasMovement() {
        return hasMovement;
    }

    public List<ElectronicEquipment> getEquipments() {
        return equipments;
    }
}
