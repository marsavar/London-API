package LondonAPI.London;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


@RestController
public class London {
    final Logger log = LoggerFactory.getLogger(LondonApplication.class);
    @GetMapping("/")
    @ResponseBody
    public HashMap london() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://bpdts-test-app.herokuapp.com/user/id";
        String response = restTemplate.getForEntity(fooResourceUrl, String.class).getBody();

        HashMap<String,Object> result =
                new ObjectMapper().readValue(response, HashMap.class);

        log.info(response);

        return result;

    }

}
