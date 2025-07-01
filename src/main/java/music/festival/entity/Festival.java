package music.festival.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Festival {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long festivalId;
	private String festivalName;
	private String festivalEdition;
	private String festivalCountry;
	private String festivalCity;
	private String festivalVenue;
	private String festivalTheme;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="festival", cascade=CascadeType.ALL, orphanRemoval= true)
	private Set<Stage> stages= new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany (cascade=CascadeType.PERSIST)// Persist denotes that you want to delete all PetStores but not the Customers
	@JoinTable (name="festival_artist", 
			joinColumns = @JoinColumn(name="festival_id"), 
			inverseJoinColumns= @JoinColumn(name="artist_id"))  //Noting that ManytoMany here does not require mapping, I already put it in Customer
	private Set<Artist> artists = new HashSet<>();

	
}
