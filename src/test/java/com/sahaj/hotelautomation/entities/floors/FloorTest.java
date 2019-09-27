package com.sahaj.hotelautomation.entities.floors;

import com.sahaj.hotelautomation.entities.corridors.MainCorridor;
import com.sahaj.hotelautomation.entities.corridors.SubCorridor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.State;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FloorTest {

    @Test
    public void defaultConsumptionWithZeroCorridors() {
        Floor floor = Floor.builder()
                        .mainCorridors(Arrays.asList())
                        .subCorridors(Arrays.asList())
                        .build();
        assertEquals(0, floor.consumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void defaultConsumptionWithOneMainAndOneSubCorridor() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.ON, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10))
                ).build();

        SubCorridor subCorridor = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10)
                )).build();

        Floor floor = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor))
                .build();

        assertEquals(25, floor.consumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void defaultConsumptionWithOneMainAndTwoSubCorridors() {
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

        SubCorridor subCorridor2 = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10)
                )).build();

        Floor floor = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();

        assertEquals(35, floor.consumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }
}