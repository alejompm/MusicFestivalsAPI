package music.festival.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Performance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long performanceId;
	private String performanceDay;
	private String performanceStartTime;
	private String performanceDuration;

	@EqualsAndHashCode.Exclude // to avoid errors when creating a JSON.. due to the recursions in entities.
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "artist_id")
	private Artist artist;
	
	@EqualsAndHashCode.Exclude // to avoid errors when creating a JSON.. due to the recursions in entities.
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "stage_id")
	private Stage stage;

	
}
