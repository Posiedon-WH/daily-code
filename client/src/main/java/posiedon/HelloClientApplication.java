package posiedon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Spencer Gibb
 */
@SpringBootApplication
//@EnableEurekaServer
//@RestController
//@EnableFeignClients
public class HelloClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

}
