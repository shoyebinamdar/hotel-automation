package hotel;

import corridors.MainCorridor;
import corridors.SubCorridor;
import equipments.AirConditioner;
import equipments.Light;
import org.junit.Assert;
import org.junit.Test;
import utils.State;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HotelControllerTest {

    @Test
    public void testConsumptionWithMovementInOneCorridor() {
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

        Floor floor1 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1))
                .build();
        Floor floor2 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor2))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Arrays.asList(floor1, floor2))
                .build();

        HotelController hotelController = new HotelController(hotel);
        hotelController.addNotifiers();

        floor1.movementDetected(subCorridor1);

        assertEquals(45, hotelController.consumption());
    }

    @Test
    public void testConsumptionWithMovementInTwoCorridors() {
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

        SubCorridor subCorridor3 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        SubCorridor subCorridor4 = SubCorridor.builder()
                .light(new Light(State.OFF, 5))
                .airConditioner(new AirConditioner(State.ON, 10))
                .build();

        Floor floor1 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1, subCorridor2))
                .build();
        Floor floor2 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor3, subCorridor4))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Arrays.asList(floor1, floor2))
                .build();

        HotelController hotelController = new HotelController(hotel);
        hotelController.addNotifiers();

        floor1.movementDetected(subCorridor1);

        assertEquals(65, hotelController.consumption());

        floor1.movementDetected(subCorridor2);

        assertEquals(70, hotelController.consumption());
    }

    @Test
    public void testConsumptionWithMovementFollowedByNoMovement() {
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

        Floor floor1 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor1))
                .build();
        Floor floor2 = Floor.builder()
                .mainCorridors(Arrays.asList(mainCorridor))
                .subCorridors(Arrays.asList(subCorridor2))
                .build();

        Hotel hotel = Hotel.builder()
                .floors(Arrays.asList(floor1, floor2))
                .build();

        HotelController hotelController = new HotelController(hotel);
        hotelController.addNotifiers();

        floor1.movementDetected(subCorridor1);

        assertEquals(45, hotelController.consumption());

        floor1.noMovementDetected();

        assertEquals(50, hotelController.consumption());
    }
}