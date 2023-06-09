package pet.park.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable //
@Data //create getters/setters, toString, hashcode, equals methods
@NoArgsConstructor //creates constructor with no parameters; helps with JSON converting
public class GeoLocation {
	private BigDecimal latitude; //like a double with a fixed number of decimal points
	private BigDecimal longitude;
	
	//copy constructor
	public GeoLocation(GeoLocation geoLocation) {
		//will be used when programming operations
		this.latitude = geoLocation.latitude;
		this.longitude = geoLocation.longitude;
	}
}
