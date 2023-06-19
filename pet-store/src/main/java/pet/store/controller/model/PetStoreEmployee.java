package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;

@Data
@NoArgsConstructor
public class PetStoreEmployee {

/* Copy the fields from the Employee entity into this class. Do not copy the petStore 
 * field to avoid recursion. Add the @Data and @NoArgsConstructor as class-level 
 * annotations. 
 * For PetStoreEmployee, add a constructor that takes an Employee object.*/
	
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhone;
	private String employeeJobTitle;
	
	PetStoreEmployee (Employee employee){
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeePhone = employee.getEmployeePhone();
		employeeJobTitle = employee.getEmployeeJobTitle();
	}
	
}