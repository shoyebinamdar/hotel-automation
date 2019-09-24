package equipments;

import utils.State;

public class AirConditioner implements ElectronicEquipment {
    State state;

    public AirConditioner(State state) {
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
        state = State.AC_ON;
    }

    @Override
    public void turnOff() {
        state = State.AC_OFF;
    }
}
