package com.sahaj.hotelautomation.equipments;

import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.State;

public class ElectronicEquipment {
    private State state;
    private final int consumption;
    public final EquipmentType TYPE;

    public ElectronicEquipment(EquipmentType type, State state, int consumption) {
        this.TYPE = type;
        this.state = state;
        this.consumption = consumption;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getConsumption() {
        return this.state == State.ON ? this.consumption : 0;
    }

    public void on() {
        this.state = State.ON;
    }

    public void off() {
        this.state = State.OFF;
    }
}
