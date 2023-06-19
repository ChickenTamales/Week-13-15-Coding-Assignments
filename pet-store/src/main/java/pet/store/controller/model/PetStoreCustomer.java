package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;


@Data
@NoArgsConstructor
public class PetStoreCustomer {

/* Copy the fields from the Customer entity into this class. Do not copy the 
 * petStores field, which will remove the recursion required by JPA. Add the @Data 
 * and @NoArgsConstructor as class-level annotations. 
 * For PetStoreCustomer, add a constructor that takes a Customer object. */
	
	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	
	PetStoreCustomer (Customer customer){
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
		customerEmail = customer.getCustomerEmail();
	}
	
}