package com.sahaj.hotelautomation.controller;

import com.sahaj.hotelautomation.entities.Hotel;
import com.sahaj.hotelautomation.entities.sensors.MotionSensor;
import com.sahaj.hotelautomation.services.MovementService;
import com.sahaj.hotelautomation.utils.HotelFactory;
import com.sahaj.hotelautomation.equipments.EquipmentState;
import org.junit.Test;

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
        assertEquals(EquipmentState.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(0).getEquipmentState());
        assertEquals(EquipmentState.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(1).getEquipmentState());
        assertEquals(EquipmentState.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(0).getEquipmentState());
        assertEquals(EquipmentState.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(1).getEquipmentState());
    }

    @Test
    public void shouldGiveValidConsumptionWhenMovementFollowedByNoMovement() throws Exception {
        Hotel hotel = HotelFactory.create(1, 1, 2);

        HotelController hotelController = new HotelController(hotel);

        hotel.getFloors().get(0).registerCorridors();
        sensor.registerCorridor(hotel.getFloors().get(0).getSubCorridors().get(0));

        movementService.triggerMovement(sensor);

        assertEquals(30, hotelController.consumption());
        assertEquals(EquipmentState.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(0).getEquipmentState());
        assertEquals(EquipmentState.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(1).getEquipmentState());
        assertEquals(EquipmentState.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(0).getEquipmentState());
        assertEquals(EquipmentState.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(1).getEquipmentState());

        movementService.triggerStagnation(sensor);

        assertEquals(35, hotelController.consumption());
        assertEquals(EquipmentState.OFF, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(0).getEquipmentState());
        assertEquals(EquipmentState.ON, hotel.getFloors().get(0).getSubCorridors().get(0).getEquipments().get(1).getEquipmentState());
        assertEquals(EquipmentState.OFF, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(0).getEquipmentState());
        assertEquals(EquipmentState.ON, hotel.getFloors().get(0).getSubCorridors().get(1).getEquipments().get(1).getEquipmentState());
    }
}