package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

/* 
 *  Add the class-level annotation @Service from the org.springframework.stereotype 
 *  package. */

@Service
public class PetStoreService {

/* 
 *  Add a PetStoreDao object named petStoreDao as a private instance variable. 
 *  Annotate the instance variable with @Autowired so that Spring can inject the DAO 
 *  object into the variable. 
 * 
 *  the savePetStore method should take a PetStoreData object as a parameter and 
 *  return a PetStoreData object.
 * 
 *  Call findOrCreatePetStore(petStoreId). This method returns a new PetStore object 
 *  if the pet store ID is null. If not null, the method should call 
 *  findPetStoreById, which returns a PetStore object if a pet store with matching ID 
 *  exists in the database. If no matching pet store is found, the method should 
 *  throw a NoSuchElementException with an appropriate message. 
 *  
 *  Call copyPetStoreFields(). This method takes a PetStore object and a PetStoreData 
 *  object as parameters. Matching fields are copied from the PetStoreData object to 
 *  the PetStore object. Do not copy the customers or employees fields. 
 *  
 *  Call the PetStoreDao method save(petStore). Return a new PetStoreData object 
 *  created from the return value of the save() method. 
 *  
 * */	
	
	@Autowired
	private PetStoreDao petStoreDao;

	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {

		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		copyPetStoreFields(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}
	
	private PetStore findOrCreatePetStore(Long petStoreId) {

		PetStore petStore;
		
		if(Objects.isNull(petStoreId)) { // if petStoreId is null...
			petStore = new PetStore();	
		}
		else {
			petStore = findPetStoreById(petStoreId);
		}
		return petStore;
	}


	private PetStore findPetStoreById(Long petStoreId) {
		
		return petStoreDao.findById(petStoreId).orElseThrow(
				() -> new NoSuchElementException(
						"Pet store with ID =" + petStoreId + " was not found"));
	}
	

}
