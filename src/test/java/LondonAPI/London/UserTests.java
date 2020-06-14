package LondonAPI.London;

import LondonAPI.London.UserClass.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    @Test
    public void testConstructor() {

        User JohnDoe = new User(1,"John", "Doe",
                "john.doe@gmail.com","127.0.0.1",51.5,-0.1,"London");

        assertEquals(1,JohnDoe.getId());
        assertEquals("John",JohnDoe.getFirst_name());
        assertEquals("Doe",JohnDoe.getLast_name());
        assertEquals("127.0.0.1",JohnDoe.getIp_address());
        assertEquals(51.5,JohnDoe.getLatitude());
        assertEquals(-0.1,JohnDoe.getLongitude());
        assertEquals("London",JohnDoe.getCity());

    }

}
