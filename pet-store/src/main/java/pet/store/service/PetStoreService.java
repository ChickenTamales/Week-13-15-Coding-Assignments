 package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

/* 
 *  Add the class-level annotation @Service from the org.springframework.stereotype 
 *  package. */

@Service
public class PetStoreService {

/* 
 *  Add a PetStoreDao object named petStoreDao as a private instance variable. 
 *  Annotate the instance variable with @Autowired so that Spring can inject the 
 *  DAO object into the variable. The savePetStore method should take 
 *  PetStoreData object as a parameter and return a PetStoreData object.
 * 
 *  Call findOrCreatePetStore(petStoreId). This method returns a new PetStore 
 *  object if the pet store ID is null. If not null, the method should call 
 *  findPetStoreById, which returns a PetStore object if a pet store with 
 *  matching ID exists in the database. If no matching pet store is found, the 
 *  method should throw a NoSuchElementException with an appropriate message. 
 *  
 *  Call copyPetStoreFields(). This method takes a PetStore object and a 
 *  PetStoreData object as parameters. Matching fields are copied from the 
 *  PetStoreData object to the PetStore object. Do not copy the customers or 
 *  employees fields. 
 *  
 *  Call the PetStoreDao method save(petStore). Return a new PetStoreData object 
 *  created from the return value of the save() method. 
 *  
 * */	
	
	@Autowired
	private PetStoreDao petStoreDao;

	private PetStore findOrCreatePetStore(Long petStoreId) {
		
		if(Objects.isNull(petStoreId)) { // if petStoreId is null...
			return new PetStore();	
		}
		else {
			return findPetStoreById(petStoreId);
		}
	}

	private PetStore findPetStoreById(Long petStoreId) {
		
		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException(
						"Pet store with ID =" + petStoreId + " was not found"));
	}
	

	
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {

		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		copyPetStoreFields(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	
	/* Inject the EmployeeDao object into the pet store service class using the 
	 * @Autowired annotation. 
	 * 
	 * Add the @Transactional annotation as a method-level annotation from the 
	 * org.springframework.transaction.annotation package. Set the readOnly 
	 * attribute to false.
	 * 
	 **************** Create a method named findEmployeeById.
	 * 
	 * It should take the pet store ID and the employee ID as parameters.
	 * Use the employeeDAO method findById() to return the Employee object. If 
	 * the employee isn't found throw a new NoSuchElementException(). 
	 * 
	 * If the pet store ID in the Employee object's PetStore variable does not 
	 * match the given pet store ID, throw a new IllegalArgumentException. 
	 * 
	 * If everything is OK, the method should return the Employee object.
	 * 
	 **************** Create a new method findOrCreateEmployee().  
	 * 
	 * This method should take an employee ID as a parameter (this will be null 
	 * if the employee is being created), as well as the pet store ID. It will 
	 * return an Employee object if successful. 
	 * 
	 * If the pet store ID is null, it should return a new Employee object. 
	 * 
	 * If the pet store ID is not null, it should call the method, 
	 * findEmployeeById(). 
	 * 
	 **************** Create a new method copyEmployeeFields. 
	 *
	 * The method should take an Employee as a parameter and a PetStoreEmployee 
	 * as a parameter. 
	 * 
	 * Copy all matching PetStoreEmployee fields to the Employee object.
	 *  
	 **************** Add a method named saveEmployee.  
	 *
	 * This method should take a pet store ID and a PetStoreEmployee object as 
	 * parameters. It must return a PetStoreEmployee object. 
	 * 
	 * Call findPetStoreById to find the pet store object. 
	 * 
	 * Call findOrCreateEmployee to retrieve an existing employee or to create a 
	 * new one. 
	 * 
	 * Call copyEmployeeFields to copy the data in the pet store employee 
	 * parameter (which ultimately came from the JSON in the HTTP POST request 
	 * payload) to the Employee object. 
	 * 
	 * Set the PetStore object in the Employee object. 
	 * 
	 * Add the Employee object into the Set of Employee objects in the PetStore 
	 * object. 
	 * 
	 * Save the employee by calling the save method in the employee DAO. 
	 * 
	 * Convert the Employee object returned by the save method to a 
	 * PetStoreEmployee object and return it. 
	 *  
	 * */
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
		if(Objects.isNull(employeeId)) { // if employeeId is null...
			return new Employee();	
		}
		else {
			return findEmployeeById(petStoreId, employeeId);
		}
	}	
	
	public Employee findEmployeeById(Long petStoreId, Long employeeId) {
		return employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException(
						"Employee with ID =" + employeeId + " was not found"));
	}

	private void copyEmployeeFields(Employee employee, 
			PetStoreEmployee petStoreEmployee) {
		employee.setEmployeeId(petStoreEmployee.getEmployeeId());
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
		employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
		employee.setEmployeeEmail(petStoreEmployee.getEmployeeEmail());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
	}
	
	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, 
			PetStoreEmployee petStoreEmployee) {
		
		PetStore petStore = findPetStoreById(petStoreId);
		Long employeeId = petStoreEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(petStoreId, employeeId);
		
		copyEmployeeFields(employee, petStoreEmployee);	
		
		employee.setPetStore(petStore);
		petStore.getEmployees().add(employee);
	
		Employee dbEmployee = employeeDao.save(employee);
		return new PetStoreEmployee(dbEmployee);
	}
	
	/* In this section, you will add a customer to an existing pet store. 
	 * Follow the instructions in the "Add store employee" section, above using 
	 * the pseudocode instructions modified to use customer instead of employee. 
	 * Modify the instructions to add the customer. Note that the controller and 
	 * service methods should use a PetStoreCustomer DTO instead of 
	 * PetStoreEmployee. (You have already created PetStoreCustomer in the 
	 * pet.store.controller.model package.)
	 * 
	 * Note that customer and pet store have a many-to-many relationship. This 
	 * means that a Customer object has a list of PetStore objects. This means 
	 * that, in the method findCustomerById, you will need to loop through the 
	 * list of PetStore objects looking for the pet store with the given pet 
	 * store ID. If not found, throw an IllegalArgumentException.
	 *  
	 *  */
	
	@Autowired
	private CustomerDao customerDao;
	
	private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		if(Objects.isNull(customerId)) { // if customerId is null...
			return new Customer();	
		}
		else {
			return findCustomerById(petStoreId, customerId);
		}
	}	
	
	public Customer findCustomerById(Long petStoreId, Long customerId) {
		
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException(
						"Customer with ID =" + customerId + " was not found"));
		
		boolean found = false;
		
		for (PetStore petStore : customer.getPetStores()) {
			
			if (petStore.getPetStoreId() == petStoreId) { 
				found = true; 
				break;
			}else {
				if(!found) {
				throw new IllegalArgumentException(
				"Customer with ID =" + customerId 
				+ " is not a member of the pet store with ID =" + petStoreId);
				}
			}
		}
		return customer;
	}

	private void copyCustomerFields(Customer customer, 
			PetStoreCustomer petStoreCustomer) {
		customer.setCustomerId(petStoreCustomer.getCustomerId());
		customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
		customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
	}
	
	@Transactional(readOnly = false)
	public PetStoreCustomer saveCustomer(Long petStoreId, 
			PetStoreCustomer petStoreCustomer) {
		
		PetStore petStore = findPetStoreById(petStoreId);
		Long customerId = petStoreCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(petStoreId, customerId);
		
		copyCustomerFields(customer, petStoreCustomer);	
		
		customer.getPetStores().add(petStore); 
		petStore.getCustomers().add(customer);
	
		Customer dbCustomer = customerDao.save(customer);
		return new PetStoreCustomer(dbCustomer);
	}

	/* In the service class method retrieveAllPetStores(): 
	 * Add the @Transactional annotation. 
	 * Call the findAll() method in the pet store DAO. Convert the List of 
	 * PetStore objects to a List of PetStoreData objects. 
	 * Remove all customer and employee objects in each PetStoreData object. 
	 * 
	 * */
	
	@Transactional
	public List<PetStoreData> retrieveAllPetStores() {
		
		List<PetStore> petStores = petStoreDao.findAll();
		List<PetStoreData> result = new LinkedList<>();
		
				for(PetStore petStore : petStores) {
					PetStoreData psd = new PetStoreData(petStore);
							psd.getCustomers().clear();
							psd.getEmployees().clear();
					result.add(psd);
				}
				return result;
	}

	/* In the service class method, add an @Transactional annotation. Call the 
	 * find by ID method written earlier and convert the results to a 
	 * PetStoreData object. 
	 * 
	 * */
	
	@Transactional
	public PetStoreData retrievePetStoreById(Long petStoreId) {
				
		PetStore petStores = petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException(
                "Pet store with ID = " + petStoreId + " was not found."
        ));
		PetStoreData psd = new PetStoreData(petStores);
					
		return psd;
		
	
	/* 
	 * Add the deletePetStoreById() method in the service class. 
	 * 
	 * Call findPetStoreById() to retrieve the PetStore entity. 
	 * 
	 * Call the delete() method in the PetStoreDao interface, passing in the 
	 * PetStore entity. 
	 * 
	 * */
		
	}
	@Transactional
	public void deletePetStoreById(Long petStoreId) {
		
		PetStore petStore = findPetStoreById(petStoreId);
				petStoreDao.delete(petStore);
			}		
	}

	


	






	


