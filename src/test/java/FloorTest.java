import corridors.MainCorridor;
import corridors.SubCorridor;
import equipments.AirConditioner;
import equipments.Light;
import hotel.Floor;
import org.junit.Test;
import utils.State;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FloorTest {

    @Test
    public void defaultConsumptionWithZeroCorridors() {
        Floor floor = Floor.builder()
                        .mainCorridors(Arrays.asList())
                        .subCorridors(Arrays.asList())
                        .build();
        assertEquals(0, floor.consumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void defaultConsumptionWithOneMainAndOneSubCorridor() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .light(new Light(State.ON, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();
        Floor floor = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor))
                .build();

        assertEquals(25, floor.consumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }

    @Test
    public void defaultConsumptionWithOneMainAndTwoSubCorridors() {
        MainCorridor mainCorridor = MainCorridor.builder()
                .light(new Light(State.ON, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor1 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();
        SubCorridor subCorridor2 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();
        Floor floor = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();

        assertEquals(35, floor.consumption());
        assertTrue(floor.isConsumptionWithinLimit());
    }
}