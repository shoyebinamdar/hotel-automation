package com.sahaj.hotelautomation.output;

import com.sahaj.hotelautomation.entities.floors.Floor;
import com.sahaj.hotelautomation.entities.Hotel;
import com.sahaj.hotelautomation.entities.corridors.MainCorridor;
import com.sahaj.hotelautomation.entities.corridors.SubCorridor;

import java.util.List;

public class ConsoleOutputListener implements OutputListener {
    @Override
    public void write(Hotel hotel) {
        List<Floor> floors = hotel.getFloors();
        for (int i = 0 ; i < floors.size(); i++) {
            System.out.println("Floor " + (i + 1));
            Floor floor = floors.get(i);
            List<MainCorridor> mainCorridors = floor.getMainCorridors();
            List<SubCorridor> subCorridors = floor.getSubCorridors();

            for (int j = 0; j < mainCorridors.size(); j++) {
                System.out.println("Main corridor " + (j + 1));
                MainCorridor mainCorridor = mainCorridors.get(j);
                mainCorridor.getEquipments().forEach(e -> System.out.println(e.TYPE + " : " + e.getState()));
            }

            for (int k = 0; k < subCorridors.size(); k++) {
                System.out.println("Sub corridor " + (k + 1));
                SubCorridor subCorridor = subCorridors.get(k);
                subCorridor.getEquipments().forEach(e -> System.out.println(e.TYPE + " : " + e.getState()));
            }
        }
    }
}
