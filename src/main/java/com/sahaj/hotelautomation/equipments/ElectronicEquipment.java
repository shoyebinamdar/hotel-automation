package com.sahaj.hotelautomation.equipments;

import com.sahaj.hotelautomation.utils.State;

public interface ElectronicEquipment {
    State getState();
    int getConsumption();
    void on();
    void off();
}
