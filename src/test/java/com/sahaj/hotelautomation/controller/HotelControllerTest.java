package com.sahaj.hotelautomation.controller;

import com.sahaj.hotelautomation.entities.Hotel;
import com.sahaj.hotelautomation.entities.corridors.MainCorridor;
import com.sahaj.hotelautomation.entities.corridors.SubCorridor;
import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.entities.sensors.MotionSensor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.services.MovementService;
import com.sahaj.hotelautomation.utils.EquipmentType;
import com.sahaj.hotelautomation.utils.HotelFactory;
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
        Hotel hotel = HotelFactory.create(2, 1, 2);

        HotelController hotelController = new HotelController(hotel);

        hotel.getFloors().get(0).registerCorridors();
        sensor.registerCorridor(hotel.getFloors().get(0).getSubCorridors().get(0));

        movementService.triggerMovement(sensor);

        assertEquals(65, hotelController.consumption());
        assertEquals(State.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(0).getState());
        assertEquals(State.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(1).getState());
        assertEquals(State.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(0).getState());
        assertEquals(State.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(1).getState());
    }

    @Test
    public void shouldGiveValidConsumptionWhenMovementFollowedByNoMovement() throws Exception {
        Hotel hotel = HotelFactory.create(1, 1, 2);

        HotelController hotelController = new HotelController(hotel);

        hotel.getFloors().get(0).registerCorridors();
        sensor.registerCorridor(hotel.getFloors().get(0).getSubCorridors().get(0));

        movementService.triggerMovement(sensor);

        assertEquals(30, hotelController.consumption());
        assertEquals(State.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(0).getState());
        assertEquals(State.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(1).getState());
        assertEquals(State.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(0).getState());
        assertEquals(State.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(1).getState());

        movementService.triggerStagnation(sensor);

        assertEquals(35, hotelController.consumption());
        assertEquals(State.OFF, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(0).getState());
        assertEquals(State.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(1).getState());
        assertEquals(State.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(0).getState());
        assertEquals(State.ON, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(1).getState());
    }
}