package hotel;

import java.util.List;

public class Hotel implements ObserverInterface {
    List<Floor> floors;

    public Hotel(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public void update(Floor floor) {
        floors.stream().filter(c -> c.equals(floor)).findFirst().ifPresent(Floor::computeDefaultConsumption);
    }

}
