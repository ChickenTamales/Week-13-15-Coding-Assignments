package pet.park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.park.controller.model.ContributorData;
import pet.park.service.ParkService;

@RestController /*tells Spring this is a REST controller. Says that every method will return a 200 or OK status by default and that we are expecting JSON to come into the method and will return JSON as well*/
@RequestMapping("/pet_park") /*tells how to map the URIs into the methods. Each method will start with pet_park.*/ 
@Slf4j //logger
public class ParkController {
	@Autowired /*asking Spring to inject the class that it creates. I want Spring to manage this object for me*/
	private ParkService parkService;

	@PostMapping("/contributor") /*this method is going to get a post request to /pet_park /contributor (/application name /resource*/
	@ResponseStatus(code = HttpStatus.CREATED)
	
	public ContributorData insertContributor(
	@RequestBody ContributorData contributorData) { /*tell Spring Boot that JSON is going to be in the request payload/body*/
		log.info("Creating contributor {}", contributorData); /*log message at the info level, default level for Spring Boot*/
		return parkService.saveContributor(contributorData);
	}
}
