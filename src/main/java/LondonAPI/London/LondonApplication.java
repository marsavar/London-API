package LondonAPI.London;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LondonApplication {

	private static final Logger log = LoggerFactory.getLogger(LondonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LondonApplication.class, args);
	}

}