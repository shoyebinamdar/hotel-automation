package com.sahaj.hotelautomation.controller;

import com.sahaj.hotelautomation.corridors.Corridor;
import com.sahaj.hotelautomation.corridors.MainCorridor;
import com.sahaj.hotelautomation.corridors.SubCorridor;
import com.sahaj.hotelautomation.utils.State;
import lombok.Builder;

import java.util.List;

@Builder
public class Floor {
    private List<MainCorridor> mainCorridors;
    private List<SubCorridor> subCorridors;
    private ControllerInterface observer;

    public void movementDetected(Corridor corridor) {
        subCorridors.stream().filter(c -> c.equals(corridor)).findFirst().ifPresent(SubCorridor::movementDetected);
        notifyController();

        if (!isConsumptionWithinLimit()) {
            subCorridors.stream().filter(c -> !c.equals(corridor) || c.getACState() == State.ON).findFirst().ifPresent(SubCorridor::turnOffAC);
            notifyController();
        }
    }

    public void noMovementDetected() {
        subCorridors.stream().forEach(SubCorridor::noMovementDetected);
        notifyController();
    }

    public int consumption() {
        return mainCorridors.stream().map(c -> c.getConsumption()).reduce(0, Integer::sum) +
                subCorridors.stream().map(c -> c.getConsumption()).reduce(0, Integer::sum);
    }

    public boolean isConsumptionWithinLimit() {
        return consumption() <= maximumAllowedConsumption();
    }

    public void notifyController() {
        this.observer.update(this);
    }

    private int maximumAllowedConsumption() {
        return this.mainCorridors.size() * 15 + this.subCorridors.size() * 10;
    }

    public void attachObserver(ControllerInterface observer) {
        this.observer = observer;
    }
}
