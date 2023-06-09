package pet.park.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.park.entity.Amenity;
import pet.park.entity.Contributor;
import pet.park.entity.GeoLocation;
import pet.park.entity.PetPark;

@Data // will create getters & setters for us
@NoArgsConstructor
public class ContributorData {

	private Long contributorId; // contributor_id (snake case) in an update we will have ContributorId, but in
								// an INSERT we will not
	private String contributorName;
	private String contributorEmail;
	private Set<PetParkResponse> petParks = new HashSet<>();

	public ContributorData(Contributor contributor) {/* convert from a contributor object to a contributorData object */
		contributorId = contributor.getContributorId();
		contributorName = contributor.getContributorName();
		contributorEmail = contributor.getContributorEmail();

		for (PetPark petPark : contributor.getPetParks()) {
			petParks.add(new PetParkResponse(petPark));
		}
	}

	@Data
	@NoArgsConstructor // lombok annotation, adds getters and a constructor with all the fields in it
	static class PetParkResponse {
		private Long petParkId;
		private String petParkName;
		private String directions;
		private String stateOrProvince;
		private String country;
		private GeoLocation geoLocation;
		private Set<String> amenities = new HashSet<>();

		PetParkResponse(PetPark petPark) {
			petParkId = petPark.getPetParkId();
			petParkName = petPark.getPetParkName();
			directions = petPark.getDirections();
			stateOrProvince = petPark.getStateOrProvince();
			country = petPark.getCountry();
			geoLocation = new GeoLocation(petPark.getGeoLocation());

			for (Amenity amenity : petPark.getAmenities()) {
				amenities.add(amenity.getAmenity());
			}
		}
	}
}
