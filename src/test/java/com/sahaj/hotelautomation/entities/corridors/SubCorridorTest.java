package com.sahaj.hotelautomation.entities.corridors;

import com.sahaj.hotelautomation.entities.corridors.SubCorridor;
import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.State;
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

    @Test
    public void shouldThrowExceptionWhenStateIsIllegal() throws Exception {
        MainCorridor mainCorridor = MainCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.ON, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10))
                ).build();

        SubCorridor subCorridor1 = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10)
                )).build();

        Floor floor1 = Floor.builder()
                .mainCorridors(Collections.singletonList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1))
                .build();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Illegal state");

        floor1.movementDetected(subCorridor1);
    }
}