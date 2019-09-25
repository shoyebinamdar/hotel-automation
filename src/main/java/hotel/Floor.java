package hotel;

import corridors.Corridor;
import corridors.MainCorridor;
import corridors.SubCorridor;

import java.util.List;

public class Floor {
    private List<MainCorridor> mainCorridors;
    private List<SubCorridor> subCorridors;
    private int consumption, maximumAllowedConsumption;
    private ObserverInterface observerInterface;

    public Floor(List<MainCorridor> mainCorridors, List<SubCorridor> subCorridors) {
        this.mainCorridors = mainCorridors;
        this.subCorridors = subCorridors;

        computeDefaultConsumption();
        maximumAllowedConsumption = computeMaximumAllowedConsumption();
    }

    public void movementDetected(Corridor corridor) {
        subCorridors.stream().filter(c -> c.equals(corridor)).findFirst().ifPresent(SubCorridor::movementDetected);
        notifyController();

        if (!isConsumptionWithinLimit()) {
            subCorridors.stream().filter(c -> !c.equals(corridor)).findFirst().ifPresent(SubCorridor::turnOffAC);
            notifyController();
        }
    }

    public void noMovementDetected() {
        subCorridors.stream().forEach(SubCorridor::noMovementDetected);
        notifyController();
    }

    public int getConsumption() {
        return consumption;
    }

    public boolean isConsumptionWithinLimit() {
        return consumption <= maximumAllowedConsumption;
    }

    public void registerObserver(ObserverInterface observerInterface) {
        this.observerInterface = observerInterface;
    }

    public void unregisterObserver(ObserverInterface observerInterface) {
        this.observerInterface = observerInterface;
    }

    public void notifyController() {
        this.observerInterface.update(this);
    }

    public void computeDefaultConsumption() {
        consumption = mainCorridors.stream().map(c -> c.getCurrentConsumption()).reduce(0, Integer::sum) +
                      subCorridors.stream().map(c -> c.getCurrentConsumption()).reduce(0, Integer::sum);
    }

    private int computeMaximumAllowedConsumption() {
        return this.mainCorridors.size() * 15 + this.subCorridors.size() * 10;
    }
}
