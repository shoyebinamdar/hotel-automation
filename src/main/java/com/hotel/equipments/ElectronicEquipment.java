package com.hotel.equipments;

import com.hotel.utils.State;

public interface ElectronicEquipment {
    State getState();
    int getConsumption();
    void on();
    void off();
}
