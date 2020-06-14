package LondonAPI.London.GetResponses;

import LondonAPI.London.URLs.URLs;
import LondonAPI.London.UserClass.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RestController
public class London {


    @GetMapping("/London")
    @ResponseBody
    public List<User> london() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<User>> users = restTemplate.exchange(URLs.getLONDON(), HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        return users.getBody();

    }

}
