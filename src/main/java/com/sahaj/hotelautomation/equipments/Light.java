package com.sahaj.hotelautomation.equipments;

import com.sahaj.hotelautomation.utils.State;

public class Light implements ElectronicEquipment {
    private State state;
    private int consumption;

    public Light(State state, int consumption) {
        this.state = state;
        this.consumption = consumption;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public int getConsumption() {
        return this.state == State.ON ? this.consumption : 0;
    }

    @Override
    public void on() {
        state = State.ON;
    }

    @Override
    public void off() {
        state = State.OFF;
    }
}
