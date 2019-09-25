import corridors.MainCorridor;
import equipments.AirConditioner;
import equipments.Light;
import org.junit.Test;
import utils.State;

import static org.junit.Assert.*;

public class MainCorridorTest {
    @Test
    public void testDefaultConsumption() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .light(new Light(State.ON, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();
        assertEquals(15, mainCorridor.getConsumption());
    }
}