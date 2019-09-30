package com.sahaj.hotelautomation.equipments;

import com.sahaj.hotelautomation.utils.EquipmentType;

public class ElectronicEquipment {
    private EquipmentState equipmentState;
    private final int consumption;
    public final EquipmentType TYPE;

    public ElectronicEquipment(EquipmentType type, EquipmentState equipmentState, int consumption) {
        this.TYPE = type;
        this.equipmentState = equipmentState;
        this.consumption = consumption;
    }

    public EquipmentState getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(EquipmentState equipmentState) {
        this.equipmentState = equipmentState;
    }

    public int getConsumption() {
        return this.equipmentState == EquipmentState.ON ? this.consumption : 0;
    }

    public void on() {
        this.equipmentState = EquipmentState.ON;
    }

    public void off() {
        this.equipmentState = EquipmentState.OFF;
    }
}
