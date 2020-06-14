package LondonAPI.London;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LondonApplication.class)
public class LondonApplicationTests  {

	@Autowired
	private LondonApplication application;

	@Test
	public void contextLoads() {

		//check that the application has started running successfully

		assertThat(application).isNotNull();
	}


}