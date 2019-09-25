package equipments;

import utils.State;

public interface ElectronicEquipment {
    State getState();
    int getConsumption();
    void on();
    void off();
}
