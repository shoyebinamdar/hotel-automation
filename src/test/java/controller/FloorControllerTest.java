package controller;

import corridors.MainCorridor;
import corridors.SubCorridor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FloorControllerTest {
    @Test
    public void consumptionWithMovementInFloorOneSubCorridorTwo() {
        MainCorridor mainCorridor = new MainCorridor();
        SubCorridor subCorridor1 = new SubCorridor();
        SubCorridor subCorridor2 = new SubCorridor();

        List<MainCorridor> mainCorridors = Arrays.asList(mainCorridor);
        List<SubCorridor> subCorridors = Arrays.asList(subCorridor1, subCorridor2);

        Floor floor = new Floor(mainCorridors, subCorridors);
        ObserverInterface floorController = new FloorController(floor);

        subCorridor1.registerObserver(floorController);
        subCorridor2.registerObserver(floorController);
        floor.movementDetected(subCorridor2);

        assertEquals(30, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void consumptionWithMovementInFloorOneBothSubCorridors() {
        MainCorridor mainCorridor = new MainCorridor();
        SubCorridor subCorridor1 = new SubCorridor();
        SubCorridor subCorridor2 = new SubCorridor();

        List<MainCorridor> mainCorridors = Arrays.asList(mainCorridor);
        List<SubCorridor> subCorridors = Arrays.asList(subCorridor1, subCorridor2);

        Floor floor = new Floor(mainCorridors, subCorridors);
        ObserverInterface floorController = new FloorController(floor);

        subCorridor1.registerObserver(floorController);
        subCorridor2.registerObserver(floorController);

        floor.movementDetected(subCorridor2);

        assertEquals(30, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());

        floor.movementDetected(subCorridor1);

        assertEquals(35, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void consumptionWithMovementInFloorOneSubCorridorTwoAndThenNoMovement() {
        MainCorridor mainCorridor = new MainCorridor();
        SubCorridor subCorridor1 = new SubCorridor();
        SubCorridor subCorridor2 = new SubCorridor();

        List<MainCorridor> mainCorridors = Arrays.asList(mainCorridor);
        List<SubCorridor> subCorridors = Arrays.asList(subCorridor1, subCorridor2);

        Floor floor = new Floor(mainCorridors, subCorridors);
        ObserverInterface floorController = new FloorController(floor);

        subCorridor1.registerObserver(floorController);
        subCorridor2.registerObserver(floorController);

        floor.movementDetected(subCorridor2);

        assertEquals(30, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());

        floor.noMovementDetected();

        assertEquals(35, floor.getConsumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }
}