package hotel;

import corridors.Corridor;
import corridors.MainCorridor;
import corridors.SubCorridor;
import lombok.Builder;
import utils.State;

import java.util.List;

@Builder
public class Floor {
    private List<MainCorridor> mainCorridors;
    private List<SubCorridor> subCorridors;
    private ObserverInterface observer;

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

    public void attachObserver(ObserverInterface observer) {
        this.observer = observer;
    }
}
