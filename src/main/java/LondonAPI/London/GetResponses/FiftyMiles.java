package LondonAPI.London.GetResponses;
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
import java.util.List;

@RestController
public class FiftyMiles {

    final String url = "https://bpdts-test-app.herokuapp.com/users";
    final Logger log = LoggerFactory.getLogger(LondonApplication.class);


    @GetMapping("/FiftyMiles")
    @ResponseBody
    public List<User> fiftymiles() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<User>> users = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        String response = restTemplate.getForEntity(url, String.class).getBody();

        log.info(response);

        return users.getBody();

    }

}
