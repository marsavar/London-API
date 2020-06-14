package LondonAPI.London.GetResponses;

import LondonAPI.London.GetResponses.HelperFunctions.DistanceFromLondon;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LondonOrFiftyMiles {


    @GetMapping("/LondonOrFiftyMiles")
    @ResponseBody
    public List<User> londonOrFiftyMiles() throws IOException {

        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<User>> allUsers = restTemplate.exchange(URLs.getALL_USERS(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });

            ResponseEntity<List<User>> residentInLondon = restTemplate.exchange(URLs.getLONDON(), HttpMethod.GET,
                    null, new ParameterizedTypeReference<>() {
                    });
            List<User> Londoners = residentInLondon.getBody();

            Set<User> withinFiftyMilesSet = Objects.requireNonNull(allUsers.getBody())
                    .stream()
                    .filter(lambda -> DistanceFromLondon.distance(lambda.getLatitude(), lambda.getLongitude()) <= 50)
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            withinFiftyMilesSet.addAll(Objects.requireNonNull(Londoners));

            return new ArrayList<>(withinFiftyMilesSet);
        } catch (Exception exception) {
            throw new IOException();
        }
    }

}