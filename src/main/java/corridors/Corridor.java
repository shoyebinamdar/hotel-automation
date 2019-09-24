package corridors;

import equipments.AirConditioner;
import equipments.ElectronicEquipment;
import equipments.Light;
import utils.State;
import controller.ObserverInterface;

public abstract class Corridor {
    private ElectronicEquipment light;
    private ElectronicEquipment airConditioner;
    private ObserverInterface observerInterface;

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

    public void registerObserver(ObserverInterface observerInterface) {
        this.observerInterface = observerInterface;
    }

    public void unregisterObserver(ObserverInterface observerInterface) {
        this.observerInterface = observerInterface;
    }

    public void notifyController() {
        this.observerInterface.update();
    }

    public abstract void movementDetected();

    public abstract void noMovementDetected();

}
