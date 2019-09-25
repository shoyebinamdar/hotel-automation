import corridors.SubCorridor;
import equipments.AirConditioner;
import equipments.Light;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import utils.State;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class SubCorridorTest {
    @Test
    public void testDefaultConsumptionTest() {
        SubCorridor subCorridor = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        assertEquals(10, subCorridor.getConsumption());
    }

    @Test
    public void testConsumptionWhenMotionDetected() {
        SubCorridor subCorridor = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        subCorridor.movementDetected();

        assertEquals(15, subCorridor.getConsumption());
    }
}