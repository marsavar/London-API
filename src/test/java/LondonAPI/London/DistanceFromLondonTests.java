package LondonAPI.London;

import LondonAPI.London.GetResponses.HelperFunctions.DistanceFromLondon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceFromLondonTests {

    @Test
    public void distanceFromLondon1() {

        double latitude = 5.7204203;
        double longitude = 10.901604;

        double distanceFromLondon = DistanceFromLondon.distance(latitude, longitude);

        assertEquals(distanceFromLondon, 3226.3,0.1);

    }

    @Test
    public void distanceFromLondon2() {

        double latitude = 32.31;
        double longitude = -106.78;

        double distanceFromLondon = DistanceFromLondon.distance(latitude, longitude);

        assertEquals(distanceFromLondon, 5146,0.1);

    }

    @Test
    public void distanceFromLondon3() {

        double latitude = 51.5489435;
        double longitude = 0.3860497;

        double distanceFromLondon = DistanceFromLondon.distance(latitude, longitude);

        assertEquals(distanceFromLondon, 22.27,0.1);

    }

    @Test
    public void distanceFromLondon4() {

        double latitude = 51.6710832;
        double longitude = 0.8078532;

        double distanceFromLondon = DistanceFromLondon.distance(latitude, longitude);

        assertTrue(distanceFromLondon <= 50);

    }

    @Test
    public void distanceFromLondon5() {

        double latitude = 31.5349;
        double longitude = 22.8078532;

        double distanceFromLondon = DistanceFromLondon.distance(latitude, longitude);

        assertFalse(distanceFromLondon <= 50);

    }
}
