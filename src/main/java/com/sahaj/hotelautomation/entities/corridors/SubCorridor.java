package com.sahaj.hotelautomation.entities.corridors;

import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.utils.EquipmentType;
import lombok.Builder;

import java.util.List;

@Builder
public class SubCorridor implements Corridor {
    private List<ElectronicEquipment> equipments;
    private boolean hasMovement;
    private Floor floor;

    @Override
    public int getConsumption() {
        return equipments.stream()
                .map(electronicEquipment -> electronicEquipment.getConsumption())
                .reduce(0, Integer::sum);
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

    @Override
    public void onActivity() throws Exception {
        this.hasMovement = true;
        equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.LIGHT)
                .findFirst()
                .ifPresent(ElectronicEquipment::on);
        notifyFloor();
    }

    @Override
    public void onInactivity() throws Exception {
        this.hasMovement = false;
        notifyFloor();
    }

    @Override
    public void register(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void notifyFloor() throws Exception {
        if (hasMovement)
            this.floor.stabilise(this);
        else
            this.floor.restore();
    }

    @Override
    public void reset() {
        equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.LIGHT)
                .findFirst()
                .ifPresent(ElectronicEquipment::off);
        equipments.stream()
                .filter(electronicEquipment -> electronicEquipment.TYPE == EquipmentType.AC)
                .findFirst()
                .ifPresent(ElectronicEquipment::on);
    }
}
