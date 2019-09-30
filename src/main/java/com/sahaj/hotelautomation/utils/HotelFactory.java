package com.sahaj.hotelautomation.utils;

import com.sahaj.hotelautomation.entities.Hotel;
import com.sahaj.hotelautomation.entities.corridors.MainCorridor;
import com.sahaj.hotelautomation.entities.corridors.SubCorridor;
import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.equipments.ElectronicEquipment;
import com.sahaj.hotelautomation.equipments.EquipmentState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelFactory {
    public static Hotel create(Integer numberOfFloors, Integer numberOfMainCorridors, Integer numberOfSubCorridors) {

        List<Floor> floors = new ArrayList<>();

        for (int i = 0; i < numberOfFloors; i++) {
            List<MainCorridor> mainCorridors = new ArrayList<>();
            List<SubCorridor> subCorridors = new ArrayList<>();

            for (int j = 0; j < numberOfMainCorridors; j++) {
                mainCorridors.add(
                        MainCorridor.builder()
                                .equipments(Arrays.asList(
                                        new ElectronicEquipment(EquipmentType.LIGHT, EquipmentState.ON, 5),
                                        new ElectronicEquipment(EquipmentType.AC, EquipmentState.ON, 10)))
                                .build()
                );
            }

            for (int j = 0; j < numberOfSubCorridors; j++) {
                subCorridors.add(
                        SubCorridor.builder()
                                .equipments(Arrays.asList(
                                        new ElectronicEquipment(EquipmentType.LIGHT, EquipmentState.OFF, 5),
                                        new ElectronicEquipment(EquipmentType.AC, EquipmentState.ON, 10)
                                )).build()
                );
            }

            floors.add(Floor.builder()
                    .mainCorridors(mainCorridors)
                    .subCorridors(subCorridors)
                    .build());
        }

        return Hotel.builder().floors(floors).build();
    }
}
