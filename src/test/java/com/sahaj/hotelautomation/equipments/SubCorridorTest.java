package com.sahaj.hotelautomation.equipments;

import com.sahaj.hotelautomation.corridors.SubCorridor;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.State;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class SubCorridorTest {
    private SubCorridor subCorridor;

    @Before
    public void setup() {
        subCorridor = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10)
                )).build();
    }
    @Test
    public void shouldTestDefaultConsumption() {
        assertEquals(10, subCorridor.getConsumption());
    }

    @Test
    public void shouldTestConsumptionWhenMotionDetected() {
        subCorridor.movementDetected();
        assertEquals(15, subCorridor.getConsumption());
    }
}