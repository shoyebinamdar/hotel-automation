import controller.ObserverInterface;
import corridors.SubCorridor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class SubCorridorTest {
    @InjectMocks SubCorridor subCorridor;

    @Mock ObserverInterface observerInterface;

    @Test
    public void testDefaultConsumptionTest() {
        assertEquals(10, subCorridor.getCurrentConsumption());
    }

    @Test
    public void testConsumptionWhenMotionDetected() {
        Mockito.doNothing().when(observerInterface).update();
        subCorridor.movementDetected();

        assertEquals(15, subCorridor.getCurrentConsumption());
    }
}