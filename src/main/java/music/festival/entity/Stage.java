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
//import music.festival.entity.Festival;  -- not needed because they are in the same package

@Entity
@Data
public class Stage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stageId;
	private String stageName;
	private String stageGenre;
	private Long stageCapacity;
	private String stageCharacteristics;
	
	@EqualsAndHashCode.Exclude // to avoid errors when creating a JSON.. due to the recursions in entities.
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "festival_id")
	private Festival festival;

	/*
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="stage", cascade=CascadeType.ALL, orphanRemoval= true)
	private Set<Performance> performances= new HashSet<>();
	 */
	
}
