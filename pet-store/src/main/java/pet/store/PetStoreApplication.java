package pet.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Add the @SpringBootApplication (org.springframework.boot.autoconfigure) annotation.*/

@SpringBootApplication
public class PetStoreApplication {

	public static void main(String[] args) {
		
/*Start Spring Boot from the main() method as shown in the videos.*/
		
		SpringApplication.run(PetStoreApplication.class, args);
	}

}
