package LondonAPI.London;

import LondonAPI.London.GetResponses.FiftyMiles;
import LondonAPI.London.GetResponses.HelperFunctions.DistanceFromLondon;
import LondonAPI.London.URLs.URLs;
import LondonAPI.London.UserClass.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = FiftyMiles.class)
public class FiftyMilesTests {

    RestTemplate restTemplate = new RestTemplate();
    String baseURL = URLs.getLOCAL();

    ResponseEntity<List<User>> response = restTemplate.exchange(
            baseURL+"/FiftyMiles",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>(){});

    List<User> withinFiftyMiles = response.getBody();

    @Test
    public void testStatusCodeValueIs200() {

        // check that the response value is successful (= 200)

        assertEquals(response.getStatusCodeValue(),200);

    }

    @Test
    public void testContentType() {

        // check that the content is returned in JSON format

        assertEquals(String.valueOf(response.getHeaders().getContentType()),"application/json");

    }

    @Test
    public void testListNotEmpty() {

        assertTrue(withinFiftyMiles != null && withinFiftyMiles.size() > 0);

    }

    @Test
    public void testUserClass() {

        // check that every member is correctly instantiated as a User object

        assertEquals((int) withinFiftyMiles.stream()
                .filter(user -> user.getClass().equals(User.class)).count(), withinFiftyMiles.size());

    }

    @Test
    public void testDistance()  {

        // check that every User is within 50 miles of London

        assertEquals((int) withinFiftyMiles.stream()
        .filter(user -> DistanceFromLondon.distance(user.getLatitude(),user.getLongitude()) <= 50)
        .count(), withinFiftyMiles.size());


    }
}