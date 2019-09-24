import corridors.MainCorridor;
import corridors.SubCorridor;
import controller.Floor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FloorTest {

    @Test
    public void defaultConsumptionWithZeroCorridors() {
        List<MainCorridor> mainCorridors = Arrays.asList();
        List<SubCorridor> subCorridors = Arrays.asList();
        Floor floor = new Floor(mainCorridors, subCorridors);
        assertEquals(0, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void defaultConsumptionWithOneMainAndOneSubCorridor() {
        List<MainCorridor> mainCorridors = Arrays.asList(new MainCorridor());
        List<SubCorridor> subCorridors = Arrays.asList(new SubCorridor());
        Floor floor = new Floor(mainCorridors, subCorridors);
        assertEquals(25, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void defaultConsumptionWithOneMainAndTwoSubCorridors() {
        List<MainCorridor> mainCorridors = Arrays.asList(new MainCorridor());
        List<SubCorridor> subCorridors = Arrays.asList(new SubCorridor(), new SubCorridor());
        Floor floor = new Floor(mainCorridors, subCorridors);
        assertEquals(35, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }
}