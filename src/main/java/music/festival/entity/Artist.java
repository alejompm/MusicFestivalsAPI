package music.festival.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Artist {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long artistId;
	
	@Column(unique = true)
	private String artistName;
	
	private String artistManager;
	private String artistEmail;
	private String artistPhone;
	private String artistSpecialRequirements;


	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy="artists")  // using the name of the variable traced in Festivals Set
	private Set<Festival> festivals = new HashSet<>();
/*	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="artist", cascade=CascadeType.ALL, orphanRemoval= true)
	private Set<Performance> performances= new HashSet<>();
*/
	
}
