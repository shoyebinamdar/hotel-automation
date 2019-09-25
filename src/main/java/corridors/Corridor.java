package corridors;

import equipments.AirConditioner;
import equipments.ElectronicEquipment;
import equipments.Light;
import utils.State;

public abstract class Corridor {
    private ElectronicEquipment light;
    private ElectronicEquipment airConditioner;

    public Corridor(State lightState, State airConditionerSate) {
        this.light = new Light(lightState);
        this.airConditioner = new AirConditioner(airConditionerSate);
    }

    public int getCurrentConsumption() {
        return light.getState().getValue() + airConditioner.getState().getValue();
    }

    public ElectronicEquipment getLight() {
        return light;
    }

    public ElectronicEquipment getAirConditioner() {
        return airConditioner;
    }



    public abstract void movementDetected();

    public abstract void noMovementDetected();

}
