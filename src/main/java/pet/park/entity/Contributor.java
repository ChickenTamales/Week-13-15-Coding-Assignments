package pet.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Contributor {
	//first tell JPA where primary key column is
	//for each primary key field
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//mysql will manage this
	private Long contributorId;  //contributor_id (snake case)
	
	private String contributorName;
	
	//unique key
	@Column(unique = true) //unique key, will create an index on contributorEmail so we can't have duplicates
	private String contributorEmail;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude //takes care of the recursion
	@OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL)
	// recursion that JPA requires to manage its relationships 
	//-petparks has contributor which has petparks, etc
	private Set<PetPark> petParks = new HashSet<>();
}
