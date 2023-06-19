package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {

/* Copy the fields in the PetStore entity into this class. Do not copy any JPA 
 * annotations. Add the @Data and @NoArgsConstructor as class-level annotations. Both 
 * are found in the lombok package.
 * In the PetStoreData class, change the data type of the customers field to 
 * PetStoreCustomer. Change the data type of the employees field to PetStoreEmployee. 
 * In the PetStoreData class add a constructor that takes a PetStore as a parameter. 
 * Set all matching fields in the PetStoreData class to the data in the PetStore 
 * class. Also set the employees and customers fields to the respective 
 * PetStoreCustomer and PetStoreEmployee objects. These are Sets so use loops for 
 * this. 
 * Also set the employees and customers fields to the respective PetStoreCustomer and 
 * PetStoreEmployee objects. These are Sets so use loops for this. */
	
	private Long petStoreId;
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	private Set<PetStoreCustomer> customers = new HashSet<>();
	private Set<PetStoreEmployee> employees = new HashSet<>();
	
	public PetStoreData (PetStore petStore){
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPetStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();
		
		for (Employee employee : petStore.getEmployees()) {
			employees.add(new PetStoreEmployee(employee));
		}
		
		for (Customer customer : petStore.getCustomers()) {
			customers.add(new PetStoreCustomer(customer));
		}
	}
}