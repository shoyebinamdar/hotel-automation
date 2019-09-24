package equipments;

import utils.State;

public class Light implements ElectronicEquipment {
    private State state;

    public Light(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void turnOn() {
        state = State.LIGHT_ON;
    }

    @Override
    public void turnOff() {
        state = State.LIGHT_OFF;
    }
}
