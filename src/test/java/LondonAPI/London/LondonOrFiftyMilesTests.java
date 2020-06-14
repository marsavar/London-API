package LondonAPI.London;

import LondonAPI.London.GetResponses.HelperFunctions.DistanceFromLondon;
import LondonAPI.London.GetResponses.LondonOrFiftyMiles;
import LondonAPI.London.URLs.URLs;
import LondonAPI.London.UserClass.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LondonOrFiftyMiles.class)
public class LondonOrFiftyMilesTests {


    RestTemplate restTemplate = new RestTemplate();
    String baseURL = URLs.getLOCAL();

    ResponseEntity<List<User>> response = restTemplate.exchange(
            baseURL+"/LondonOrFiftyMiles",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>(){});

    List<User> withinFiftyMilesOrResidents = response.getBody();

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

        // check that the list is not empty

        assertTrue(withinFiftyMilesOrResidents != null && withinFiftyMilesOrResidents.size() > 0);

    }

    @Test
    public void testUserClass() {

        // check that every member is correctly instantiated as a User object

        assertEquals((int) withinFiftyMilesOrResidents.stream()
                .filter(user -> user.getClass().equals(User.class)).count(), withinFiftyMilesOrResidents.size());

    }

    @Test
    public void testDistanceOrResidence()  {

        // check that every User is within 50 miles of London OR resident in London

        ResponseEntity<List<User>> response = restTemplate.exchange(
                baseURL+"/London",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){});

        List<User> Londoners = response.getBody();

        assertEquals(Objects.requireNonNull(Londoners).size() + (int) withinFiftyMilesOrResidents.stream()
                        .filter(user -> DistanceFromLondon.distance(user.getLatitude(), user.getLongitude()) <= 50)
                        .count()
                , withinFiftyMilesOrResidents.size());


    }

    @Test
    public void testLondonUsers135()  {

        // check that user Mechelle Boam is in the list

        User mechelle = withinFiftyMilesOrResidents.stream().filter(user -> user
                .getFirst_name()
                .toLowerCase()
                .equals("mechelle"))
                .findFirst()
                .get();


        assertEquals(mechelle.getId(),135);
        assertEquals(mechelle.getFirst_name().toLowerCase(),"mechelle");
        assertEquals(mechelle.getLast_name().toLowerCase(),"boam");
        assertEquals(mechelle.getEmail().toLowerCase(),"mboam3q@thetimes.co.uk");
        assertEquals(mechelle.getIp_address().toLowerCase(),"113.71.242.187");
        assertEquals(mechelle.getLatitude(),-6.5115909);
        assertEquals(mechelle.getLongitude(),105.652983);
        assertNull(mechelle.getCity());


    }
}