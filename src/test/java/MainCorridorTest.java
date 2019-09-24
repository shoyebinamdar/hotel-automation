import corridors.MainCorridor;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainCorridorTest {
    @Test
    public void testDefaultConsumption() {
        MainCorridor mainCorridor = new MainCorridor();
        assertEquals(15, mainCorridor.getCurrentConsumption());
    }

    @Test
    public void testConsumptionWhenMotionDetected() {
        MainCorridor mainCorridor = new MainCorridor();
        mainCorridor.movementDetected();
        assertEquals(15, mainCorridor.getCurrentConsumption());
    }
}