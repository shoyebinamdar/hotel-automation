package com.sahaj.hotelautomation.output;

import com.sahaj.hotelautomation.entities.Hotel;
import com.sahaj.hotelautomation.entities.corridors.MainCorridor;
import com.sahaj.hotelautomation.entities.corridors.SubCorridor;
import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ConsoleOutputListenerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void reset() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldWriteDefaultHotelState() {
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

        Floor floor1 = Floor.builder()
                .mainCorridors(Collections.singletonList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Collections.singletonList(floor1))
                .build();

        OutputListener outputListener = new ConsoleOutputListener();
        outputListener.write(hotel);

        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "LIGHT : ON\n" +
                        "AC : ON\n" +
                        "Sub corridor 1\n" +
                        "LIGHT : OFF\n" +
                        "AC : ON\n" +
                        "Sub corridor 2\n" +
                        "LIGHT : OFF\n" +
                        "AC : ON\n"
                , outContent.toString());
    }

    @Test
    public void shouldWriteHotelStateWhenMovementInFloorOneSubCorridorOne() throws Exception {
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

        Floor floor1 = Floor.builder()
                .mainCorridors(Collections.singletonList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Collections.singletonList(floor1))
                .build();

        OutputListener outputListener = new ConsoleOutputListener();

        floor1.movementDetected(subCorridor1);
        outputListener.write(hotel);

        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "LIGHT : ON\n" +
                        "AC : ON\n" +
                        "Sub corridor 1\n" +
                        "LIGHT : ON\n" +
                        "AC : ON\n" +
                        "Sub corridor 2\n" +
                        "LIGHT : OFF\n" +
                        "AC : OFF\n"
                , outContent.toString());
    }

    @Test
    public void shouldRollBackToDefaultStateWhenMovementStopped() throws Exception {
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

        Floor floor1 = Floor.builder()
                .mainCorridors(Collections.singletonList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Collections.singletonList(floor1))
                .build();

        OutputListener outputListener = new ConsoleOutputListener();

        floor1.movementDetected(subCorridor1);
        floor1.noMovementDetected();
        outputListener.write(hotel);

        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "LIGHT : ON\n" +
                        "AC : ON\n" +
                        "Sub corridor 1\n" +
                        "LIGHT : OFF\n" +
                        "AC : ON\n" +
                        "Sub corridor 2\n" +
                        "LIGHT : OFF\n" +
                        "AC : ON\n"
                , outContent.toString());
    }
}