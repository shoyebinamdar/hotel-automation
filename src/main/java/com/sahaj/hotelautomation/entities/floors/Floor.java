package com.sahaj.hotelautomation.entities.floors;

import com.sahaj.hotelautomation.entities.corridors.Corridor;
import com.sahaj.hotelautomation.entities.corridors.MainCorridor;
import com.sahaj.hotelautomation.entities.corridors.SubCorridor;
import lombok.Builder;

import java.util.List;

@Builder
public class Floor {
    private List<MainCorridor> mainCorridors;
    private List<SubCorridor> subCorridors;

    public void movementDetected(Corridor corridor) throws Exception {
        subCorridors.stream().filter(c -> c.equals(corridor)).findFirst().ifPresent(SubCorridor::movementDetected);

        if (!isConsumptionWithinLimit()) {
            subCorridors.stream()
                    .filter(c -> !c.equals(corridor) && !c.hasMovement())
                    .findFirst()
                    .map(subCorridor -> {
                        subCorridor.turnOffAC();
                        return subCorridor;
                    })
                    .orElseThrow(() -> new Exception("Illegal state"));
        }
    }

    public void noMovementDetected() {
        subCorridors.forEach(SubCorridor::noMovementDetected);
        //emit();
    }

    public int consumption() {
        return mainCorridors.stream().map(MainCorridor::getConsumption).reduce(0, Integer::sum) +
                subCorridors.stream().map(SubCorridor::getConsumption).reduce(0, Integer::sum);
    }

    public boolean isConsumptionWithinLimit() {
        return consumption() <= maximumAllowedConsumption();
    }

    private int maximumAllowedConsumption() {
        return this.mainCorridors.size() * 15 + this.subCorridors.size() * 10;
    }

    public List<MainCorridor> getMainCorridors() {
        return mainCorridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }
}