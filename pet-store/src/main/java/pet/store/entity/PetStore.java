package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*Remember to add the @Entity (jakarta.persistence) and @Data (lombok) class-level annotations.*/

@Entity
@Data
public class PetStore {

/*Referencing your ERD, add the instance variables in each entity. Remember to convert the snake case table column names to upper camel case (so, "customer_email" in the table becomes "customerEmail" in the Java class).*/
	
/*Add the annotations @Id (jakarta.persistence) and @GeneratedValue (jakarta.persistence) to each ID field as shown in the videos.*/
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
/*Add the relationship variables into each class:*/
	
/*Add @EqualsAndHashCode.Exclude and @ToString.Exclude to all of the recursive relationship variables. This will prevent recursion from occurring when the .toString(), .equals(), or .hashCode() methods are called.*/
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "pet_store_customer", 
			joinColumns = @JoinColumn(name = "pet_store_id"),
			inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "petStore",
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private Set<Employee> employees = new HashSet<>();
}
