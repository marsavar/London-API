package LondonAPI.London;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LondonApplication {

	private static final Logger log = LoggerFactory.getLogger(LondonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LondonApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate)  {
		return args -> {
			ConsumeUser user = restTemplate.getForObject(
					"https://bpdts-test-app.herokuapp.com/user/2", ConsumeUser.class);
			log.info(user.toString());
		};
	}
}