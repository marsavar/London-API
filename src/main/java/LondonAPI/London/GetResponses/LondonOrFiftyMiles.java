package LondonAPI.London.GetResponses;

import LondonAPI.London.GetResponses.Helpers.DistanceFromLondon;
import LondonAPI.London.LondonApplication;
import LondonAPI.London.UserClass.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LondonOrFiftyMiles {

    final String allUsersURL = "https://bpdts-test-app.herokuapp.com/users";
    final String residentInLondonURL = "https://bpdts-test-app.herokuapp.com/city/London/users";
    final Logger log = LoggerFactory.getLogger(LondonApplication.class);

    @GetMapping("/LondonOrFiftyMiles")
    @ResponseBody
    public List<User> londonOrFiftyMiles() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<User>> allUsers = restTemplate.exchange(allUsersURL, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});


        ResponseEntity<List<User>> residentInLondon = restTemplate.exchange(residentInLondonURL, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {});
        List<User> Londoners = residentInLondon.getBody();

        Set<User> withinFiftyMilesSet = Objects.requireNonNull(allUsers.getBody())
                .stream()
                .filter(lambda -> DistanceFromLondon.distance(lambda.getLatitude(), lambda.getLongitude()) <= 50)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        withinFiftyMilesSet.addAll(Objects.requireNonNull(Londoners));

        return new ArrayList<>(withinFiftyMilesSet);

    }

}
