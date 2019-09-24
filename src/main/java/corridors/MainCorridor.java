package corridors;

import utils.State;

public class MainCorridor extends Corridor {
    public MainCorridor() {
        super(State.LIGHT_ON, State.AC_ON);
    }

    @Override
    public void movementDetected() { }

    @Override
    public void noMovementDetected() { }

    @Override
    public void notifyController() { }
}
