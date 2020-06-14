package LondonAPI.London.GetResponses;

import LondonAPI.London.LondonApplication;
import LondonAPI.London.URLs.URLs;
import LondonAPI.London.UserClass.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Controller
@RestController
public class London {

    private static final Logger log = LoggerFactory.getLogger(LondonApplication.class);

    @GetMapping("/London")
    @ResponseBody
    public List<User> london() throws IOException {

        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<User>> users = restTemplate.exchange(URLs.getLONDON(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });

            log.info(String.valueOf(users.getHeaders().getContentType()));
            return users.getBody();

        } catch (Exception exception) {
            throw new IOException(exception);
        }

    }

}
