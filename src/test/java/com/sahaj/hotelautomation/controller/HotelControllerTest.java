package com.sahaj.hotelautomation.controller;

import com.sahaj.hotelautomation.corridors.MainCorridor;
import com.sahaj.hotelautomation.corridors.SubCorridor;
import com.sahaj.hotelautomation.equipments.AirConditioner;
import com.sahaj.hotelautomation.equipments.Light;
import com.sahaj.hotelautomation.utils.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HotelControllerTest {

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
    public void testConsumptionWithMovementInOneCorridor() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .light(new Light(State.ON, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor1 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();
        SubCorridor subCorridor2 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        Floor floor1 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1))
                .build();
        Floor floor2 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor2))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Arrays.asList(floor1, floor2))
                .build();

        HotelController hotelController = new HotelController(hotel);
        hotelController.addNotifiers();

        floor1.movementDetected(subCorridor1);
        hotelController.printStatus();

        assertEquals(45, hotelController.consumption());
        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" + "" +
                        "Sub corridor 1\n" +
                        "Light : ON | AC : OFF\n" +
                        "Floor 2\n" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" +
                        "Sub corridor 1\n" +
                        "Light : OFF | AC : ON\n"
                , outContent.toString());
    }

    @Test
    public void testConsumptionWithMovementInTwoCorridors() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .light(new Light(State.ON, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor1 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor2 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor3 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor4 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        Floor floor1 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();
        Floor floor2 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor3, subCorridor4))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Arrays.asList(floor1, floor2))
                .build();

        HotelController hotelController = new HotelController(hotel);
        hotelController.addNotifiers();

        floor1.movementDetected(subCorridor1);
        hotelController.printStatus();

        assertEquals(65, hotelController.consumption());
        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" + "" +
                        "Sub corridor 1\n" +
                        "Light : ON | AC : ON\n" +
                        "Sub corridor 2\n" +
                        "Light : OFF | AC : OFF\n" +
                        "Floor 2\n" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" +
                        "Sub corridor 1\n" +
                        "Light : OFF | AC : ON\n" +
                        "Sub corridor 2\n" +
                        "Light : OFF | AC : ON\n"
                , outContent.toString());

        reset();
        floor1.movementDetected(subCorridor2);
        hotelController.printStatus();

        assertEquals(70, hotelController.consumption());
        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" + "" +
                        "Sub corridor 1\n" +
                        "Light : OFF | AC : ON\n" +
                        "Sub corridor 2\n" +
                        "Light : OFF | AC : ON\n" +
                        "Floor 2\n" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" +
                        "Sub corridor 1\n" +
                        "Light : OFF | AC : ON\n" +
                        "Sub corridor 2\n" +
                        "Light : OFF | AC : ON\n"
                , outContent.toString());
    }

    @Test
    public void testConsumptionWithMovementFollowedByNoMovement() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .light(new Light(State.ON, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor1 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();
        SubCorridor subCorridor2 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        Floor floor1 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1))
                .build();
        Floor floor2 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor2))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Arrays.asList(floor1, floor2))
                .build();

        HotelController hotelController = new HotelController(hotel);
        hotelController.addNotifiers();

        floor1.movementDetected(subCorridor1);
        hotelController.printStatus();

        assertEquals(45, hotelController.consumption());
        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" + "" +
                        "Sub corridor 1\n" +
                        "Light : ON | AC : ON\n" +
                        "Floor 2\n" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" +
                        "Sub corridor 1\n" +
                        "Light : OFF | AC : ON\n"
                , outContent.toString());


        reset();
        floor1.noMovementDetected();
        hotelController.printStatus();

        assertEquals(50, hotelController.consumption());
        assertEquals(
                "Floor 1\n" + "" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" + "" +
                        "Sub corridor 1\n" +
                        "Light : OFF | AC : ON\n" +
                        "Floor 2\n" +
                        "Main corridor 1\n" +
                        "Light : ON | AC : ON\n" +
                        "Sub corridor 1\n" +
                        "Light : OFF | AC : ON\n"
                , outContent.toString());
    }
}