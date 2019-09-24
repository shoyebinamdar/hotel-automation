package corridors;

import utils.State;

public class SubCorridor extends Corridor {
    public SubCorridor() {
        super(State.LIGHT_OFF, State.AC_ON);
    }

    @Override
    public void movementDetected() {
        this.getLight().turnOn();
        notifyController();
    }

    @Override
    public void noMovementDetected() {
        this.getLight().turnOff();
        this.getAirConditioner().turnOn();
        notifyController();
    }

    public void turnOffAC() {
        this.getAirConditioner().turnOff();
        notifyController();
    }
}
