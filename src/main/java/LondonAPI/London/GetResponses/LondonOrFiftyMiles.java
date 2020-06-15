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

            // get all users
            ResponseEntity<List<User>> allUsers = restTemplate.exchange(URLs.getALL_USERS(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });

            // get all users listed as residing in London
            ResponseEntity<List<User>> residentInLondon = restTemplate.exchange(URLs.getLONDON(), HttpMethod.GET,
                    null, new ParameterizedTypeReference<>() {
                    });
            List<User> Londoners = residentInLondon.getBody();

            // filter users whose coordinates are within 50 miles of London
            Set<User> withinFiftyMilesSet = Objects.requireNonNull(allUsers.getBody())
                    .stream()
                    .filter(lambda -> DistanceFromLondon.distance(lambda.getLatitude(), lambda.getLongitude()) <= 50)
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            // add users living in London to the set
            withinFiftyMilesSet.addAll(Objects.requireNonNull(Londoners));

            // convert the set to a list
            return new ArrayList<>(withinFiftyMilesSet);

        } catch (Exception exception) {
            throw new IOException();
        }
    }

}