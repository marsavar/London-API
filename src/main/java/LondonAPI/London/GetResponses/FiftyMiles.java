package LondonAPI.London.GetResponses;
import LondonAPI.London.GetResponses.Helpers.DistanceFromLondon;
import LondonAPI.London.URLs.URLs;
import LondonAPI.London.UserClass.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class FiftyMiles {

    @GetMapping("/FiftyMiles")
    @ResponseBody
    public List<User> fiftyMiles() throws IOException {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<User>> users = restTemplate.exchange(URLs.getALL_USERS(), HttpMethod.GET,
                    null, new ParameterizedTypeReference<>() {
                    });

            return Objects.requireNonNull(users.getBody())
                    .stream()
                    .filter(lambda -> DistanceFromLondon.distance(lambda.getLatitude(), lambda.getLongitude()) <= 50)
                    .collect(Collectors.toList());

        } catch (Exception exception) {
                throw new IOException(exception);
        }



    }

}
