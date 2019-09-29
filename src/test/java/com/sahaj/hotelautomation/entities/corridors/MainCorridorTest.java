package com.sahaj.hotelautomation.entities.corridors;

import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.State;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MainCorridorTest {
    @Test
    public void testDefaultConsumption() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.ON, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10))
                ).build();
        assertEquals(15, mainCorridor.getConsumption());
    }
}