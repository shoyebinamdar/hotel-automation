package hotel;

import corridors.MainCorridor;
import corridors.SubCorridor;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HotelTest {
    MainCorridor mainCorridor;
    SubCorridor subCorridor1;
    SubCorridor subCorridor2;
    List<MainCorridor> mainCorridors;
    List<SubCorridor> subCorridors;

    Floor floor;
    ObserverInterface observer;

    @Before
    public void setUp() throws Exception {
        mainCorridor = new MainCorridor();
        subCorridor1 = new SubCorridor();
        subCorridor2 = new SubCorridor();
        mainCorridors = Arrays.asList(mainCorridor);
        subCorridors = Arrays.asList(subCorridor1, subCorridor2);
        floor = new Floor(mainCorridors, subCorridors);
        observer = new Hotel(Arrays.asList(floor));
        floor.registerObserver(observer);
    }

    @Test
    public void consumptionWithMovementInFloorOneSubCorridorTwo() {
        floor.movementDetected(subCorridor2);

        assertEquals(30, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void consumptionWithMovementInFloorOneBothSubCorridors() {
        floor.movementDetected(subCorridor2);

        assertEquals(30, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());

        floor.movementDetected(subCorridor1);

        assertEquals(35, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void consumptionWithMovementInFloorOneSubCorridorTwoAndThenNoMovement() {
        floor.movementDetected(subCorridor2);

        assertEquals(30, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());

        floor.noMovementDetected();

        assertEquals(35, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }
}