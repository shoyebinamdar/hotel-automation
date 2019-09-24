package equipments;

import utils.State;

public interface ElectronicEquipment {
    State getState();
    void turnOn();
    void turnOff();
}
