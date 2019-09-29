package com.sahaj.hotelautomation.controller;

import com.sahaj.hotelautomation.entities.Hotel;
import com.sahaj.hotelautomation.entities.corridors.MainCorridor;
import com.sahaj.hotelautomation.entities.corridors.SubCorridor;
import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.entities.sensors.MotionSensor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.services.MovementService;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.State;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class HotelControllerTest {
    MovementService movementService = new MovementService();
    MotionSensor sensor = new MotionSensor();

    @Test
    public void shouldGiveValidConsumtionMovementInTwoCorridors() throws Exception {
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

        SubCorridor subCorridor3 = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10)
                ))
                .build();

        SubCorridor subCorridor4 = SubCorridor.builder()
                .equipments(Arrays.asList(
                        new ElectronicEquipment(EquipmentType.LIGHT, State.OFF, 5),
                        new ElectronicEquipment(EquipmentType.AC, State.ON, 10)
                )).build();


        Floor floor1 = Floor.builder()
                .mainCorridors(Collections.singletonList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();
        Floor floor2 = Floor.builder()
                .mainCorridors(Collections.singletonList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor3, subCorridor4))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Arrays.asList(floor1, floor2))
                .build();

        HotelController hotelController = new HotelController(hotel);

        floor1.registerCorridors();
        sensor.registerCorridor(subCorridor1);

        movementService.triggerMovement(sensor);

        assertEquals(65, hotelController.consumption());
        assertEquals(State.ON, subCorridor1.getEquipments().get(0).getState());
        assertEquals(State.ON, subCorridor1.getEquipments().get(1).getState());
        assertEquals(State.OFF, subCorridor2.getEquipments().get(0).getState());
        assertEquals(State.OFF, subCorridor2.getEquipments().get(1).getState());
    }

    @Test
    public void shouldGiveValidConsumptionWhenMovementFollowedByNoMovement() throws Exception {
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
                .floors(Arrays.asList(floor1))
                .build();

        HotelController hotelController = new HotelController(hotel);

        floor1.registerCorridors();
        sensor.registerCorridor(subCorridor1);

        movementService.triggerMovement(sensor);

        assertEquals(30, hotelController.consumption());
        assertEquals(State.ON, subCorridor1.getEquipments().get(0).getState());
        assertEquals(State.ON, subCorridor1.getEquipments().get(1).getState());
        assertEquals(State.OFF, subCorridor2.getEquipments().get(0).getState());
        assertEquals(State.OFF, subCorridor2.getEquipments().get(1).getState());

        movementService.triggerStagnation(sensor);

        assertEquals(35, hotelController.consumption());
        assertEquals(State.OFF, subCorridor1.getEquipments().get(0).getState());
        assertEquals(State.ON, subCorridor1.getEquipments().get(1).getState());
        assertEquals(State.OFF, subCorridor2.getEquipments().get(0).getState());
        assertEquals(State.ON, subCorridor2.getEquipments().get(1).getState());
    }
}