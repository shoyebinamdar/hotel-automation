package com.sahaj.hotelautomation.equipments;

import com.sahaj.hotelautomation.corridors.MainCorridor;
import com.sahaj.hotelautomation.utils.State;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainCorridorTest {
    @Test
    public void testDefaultConsumption() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .light(new Light(State.ON, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();
        assertEquals(15, mainCorridor.getConsumption());
    }
}