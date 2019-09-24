package controller;

public class FloorController implements ObserverInterface {
    Floor floor;

    public FloorController(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void update() {
        this.floor.computeDefaultConsumption();
    }
}
