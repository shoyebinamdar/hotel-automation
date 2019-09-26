package com.hotel.equipments;

import com.hotel.corridors.MainCorridor;
import com.hotel.utils.State;
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