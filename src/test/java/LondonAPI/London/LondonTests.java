package LondonAPI.London;

import LondonAPI.London.GetResponses.London;
import LondonAPI.London.URLs.URLs;
import LondonAPI.London.UserClass.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = London.class)
public class LondonTests {

    private static final Logger log = LoggerFactory.getLogger(LondonTests.class);


    RestTemplate restTemplate = new RestTemplate();
    String baseURL = URLs.getLOCAL();

    ResponseEntity<List<User>> response = restTemplate.exchange(
            baseURL+"/London",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>(){});

    List<User> Londoners = response.getBody();

    @Test
    public void testStatusCodeValueIs200() {

        // check that the response value is successful (= 200)

        assertEquals(response.getStatusCodeValue(),200);

    }

    @Test
    public void testListNotEmpty() {

        assertTrue(Londoners != null && Londoners.size() > 0);

    }

    @Test
    public void testUserClass() {

        // check that every member is correctly instantiated as a User object

        assertEquals((int) Londoners.stream()
                .filter(user -> user.getClass().equals(User.class)).count(), Londoners.size());

    }

    @Test
    public void testLondonUsers1()  {

        // check that user Mechelle Boam is in the list

        User mechelle = Londoners.stream().filter(user -> user
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