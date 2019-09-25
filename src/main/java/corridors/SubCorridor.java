package corridors;

import equipments.ElectronicEquipment;
import lombok.Builder;
import utils.State;

@Builder
public class SubCorridor implements Corridor {
    private ElectronicEquipment light, airConditioner;
    private boolean hasMovement;

    @Override
    public int getConsumption() {
        return light.getConsumption() + airConditioner.getConsumption();
    }

    public void movementDetected() {
        this.hasMovement = true;
        this.light.on();
    }
    
    public void noMovementDetected() {
        this.hasMovement = false;
        this.light.off();
        this.airConditioner.on();
    }

    public void turnOffAC() {
        this.airConditioner.off();
    }

    public State getACState() {
        return this.airConditioner.getState();
    }
}
