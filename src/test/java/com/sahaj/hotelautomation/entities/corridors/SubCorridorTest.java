package com.sahaj.hotelautomation.entities.corridors;

import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.entities.sensors.MotionSensor;
import com.sahaj.hotelautomation.entities.sensors.Sensor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.services.MovementService;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.equipments.EquipmentState;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class SubCorridorTest {
    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    private SubCorridor subCorridor;

    @Before
    public void setup() {
        subCorridor = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, EquipmentState.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, EquipmentState.ON, 10)
                )).build();
    }
    @Test
    public void shouldTestDefaultConsumption() {
        assertEquals(10, subCorridor.getConsumption());
    }

    @Test
    public void shouldThrowExceptionWhenStateIsIllegal() throws Exception {
        MainCorridor mainCorridor = MainCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, EquipmentState.ON, 5),
                        new ElectronicEquipment(EquipmentType.AC, EquipmentState.ON, 10))
                ).build();

        SubCorridor subCorridor1 = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, EquipmentState.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, EquipmentState.ON, 10)
                )).build();

        Floor floor1 = Floor.builder()
                .mainCorridors(Collections.singletonList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1))
                .build();

        Sensor sensor = new MotionSensor();
        MovementService movementService = new MovementService();

        sensor.registerCorridor(subCorridor1);
        floor1.registerCorridors();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Illegal state");

        movementService.triggerMovement(sensor);
    }
}