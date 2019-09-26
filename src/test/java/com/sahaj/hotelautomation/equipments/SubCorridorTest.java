package com.sahaj.hotelautomation.equipments;

import com.sahaj.hotelautomation.corridors.SubCorridor;
import com.sahaj.hotelautomation.utils.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class SubCorridorTest {
    @Test
    public void testDefaultConsumptionTest() {
        SubCorridor subCorridor = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        assertEquals(10, subCorridor.getConsumption());
    }

    @Test
    public void testConsumptionWhenMotionDetected() {
        SubCorridor subCorridor = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        subCorridor.movementDetected();

        assertEquals(15, subCorridor.getConsumption());
    }
}