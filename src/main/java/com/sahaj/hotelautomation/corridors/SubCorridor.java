package com.sahaj.hotelautomation.corridors;

import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.utils.EquipmentType;
import lombok.Builder;
import com.sahaj.hotelautomation.utils.State;

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

    public State getACState() {
        return equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.AC)
                .findFirst().get().getState();
    }

    public boolean hasMovement() {
        return hasMovement;
    }

    public List<ElectronicEquipment> getEquipments() {
        return equipments;
    }
}
