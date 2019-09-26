package com.sahaj.hotelautomation.controller;

import com.sahaj.hotelautomation.corridors.Corridor;
import com.sahaj.hotelautomation.corridors.MainCorridor;
import com.sahaj.hotelautomation.corridors.SubCorridor;
import lombok.Builder;

import java.util.List;

@Builder
public class Floor {
    private List<MainCorridor> mainCorridors;
    private List<SubCorridor> subCorridors;
    private ControllerInterface observer;

    public void movementDetected(Corridor corridor) throws Exception {
        subCorridors.stream().filter(c -> c.equals(corridor)).findFirst().ifPresent(SubCorridor::movementDetected);
        notifyController();

        if (!isConsumptionWithinLimit()) {
            subCorridors.stream()
                    .filter(c -> !c.equals(corridor) && !c.hasMovement())
                    .findFirst()
                    .map(subCorridor -> {
                        subCorridor.turnOffAC();
                        return subCorridor;
                    })
                    .orElseThrow(() -> new Exception("Illegal state"));
            notifyController();
        }
    }

    public void noMovementDetected() {
        subCorridors.forEach(SubCorridor::noMovementDetected);
        notifyController();
    }

    public int consumption() {
        return mainCorridors.stream().map(c -> c.getConsumption()).reduce(0, Integer::sum) +
                subCorridors.stream().map(c -> c.getConsumption()).reduce(0, Integer::sum);
    }

    public boolean isConsumptionWithinLimit() {
        return consumption() <= maximumAllowedConsumption();
    }

    private void notifyController() {
        this.observer.update(this);
    }

    private int maximumAllowedConsumption() {
        return this.mainCorridors.size() * 15 + this.subCorridors.size() * 10;
    }

    public void attachObserver(ControllerInterface observer) {
        this.observer = observer;
    }

    public List<MainCorridor> getMainCorridors() {
        return mainCorridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }
}
