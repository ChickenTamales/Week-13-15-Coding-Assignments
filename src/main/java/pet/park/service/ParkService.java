package pet.park.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.park.controller.model.ContributorData;
import pet.park.dao.ContributorDao;
import pet.park.entity.Contributor;


@Service //will create this bean and then inject it into the parkService instance variable
public class ParkService {

	@Autowired
	private ContributorDao contributorDao;
	@Transactional(readOnly = false) //start a transaction
	public ContributorData saveContributor(ContributorData contributorData) {
		//I want to either create an empty contributor object or find one in the database. checking whether the contributorId is null or not
		Long contributorId = contributorData.getContributorId();
		
		//declare Contributor entity object
		Contributor contributor = findOrCreateContributor(contributorId);
		
		return setFieldsInContributor(contributor, contributorData);
	}

	private ContributorData setFieldsInContributor(Contributor contributor, 
			ContributorData contributorData) {
		contributor.setContributorEmail(contributorData.getContributorEmail());
		contributor.setContributorName(contributorData.getContributorName());
		/*don't need to set contributorId because it will either be null for create operation or
		it will be correct for modify operation*/ 
		return new ContributorData(contributorDao.save(contributor));
	}

	private Contributor findOrCreateContributor(Long contributorId) {
		Contributor contributor;
		
		if(Objects.isNull(contributorId)) { //if contributorId is null...
			contributor = new Contributor(); //...we will create one
		}
		else { /*if it's not null*/
			contributor = findContributorById(contributorId);
		}
		return contributor;
	}
	

	private Contributor findContributorById(Long contributorId) {
		//will use a DAO object interface that extends an interface is Spring JPA in order to get this and won't have to write a method because Spring JPA has already done that for us
		return contributorDao.findById(contributorId)
				.orElseThrow(() -> new  NoSuchElementException(
						"Contributor with ID =" + contributorId + " was not found")); //returns an Optional, so add .orElsethrow - convert an optional to an actual object. If the optional is empty which means there wasn't a contributor with that id, it's going to throw an exception. If it found one, it's going to return it.
	}

}
