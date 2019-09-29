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

    public int consumption() {
        return mainCorridors.stream().map(MainCorridor::getConsumption).reduce(0, Integer::sum) +
                subCorridors.stream().map(SubCorridor::getConsumption).reduce(0, Integer::sum);
    }

    public boolean isConsumptionWithinLimit() {
        return consumption() <= threshold();
    }

    private int threshold() {
        return this.mainCorridors.size() * 15 + this.subCorridors.size() * 10;
    }

    public void stabilise(Corridor corridor) throws Exception {
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

    public void restore() {
        subCorridors.forEach(SubCorridor::reset);
    }

    public void registerCorridors() {
        mainCorridors.stream().forEach(subCorridor -> subCorridor.register(this));
        subCorridors.stream().forEach(subCorridor -> subCorridor.register(this));
    }

    public List<MainCorridor> getMainCorridors() {
        return mainCorridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }
}
