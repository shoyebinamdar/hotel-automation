import corridors.SubCorridor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class SubCorridorTest {
    @InjectMocks SubCorridor subCorridor;

    @Test
    public void testDefaultConsumptionTest() {
        assertEquals(10, subCorridor.getCurrentConsumption());
    }

    @Test
    public void testConsumptionWhenMotionDetected() {
        subCorridor.movementDetected();

        assertEquals(15, subCorridor.getCurrentConsumption());
    }
}