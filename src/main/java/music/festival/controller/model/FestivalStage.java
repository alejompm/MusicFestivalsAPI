package music.festival.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.festival.entity.Festival;
import music.festival.entity.Performance;
import music.festival.entity.Stage;

@Data
@NoArgsConstructor
public class FestivalStage {

	private Long stageId;
	private String stageName;
	private String stageGenre;
	private Long stageCapacity;
	private String stageCharacteristics;
	private StageFestival festival;
	//private Set<Performance> performances= new HashSet<>();
	
	public FestivalStage(Stage stage) {
		stageId = stage.getStageId();
		stageName=stage.getStageName();
		stageGenre=stage.getStageGenre();
		stageCapacity=stage.getStageCapacity();
		stageCharacteristics =stage.getStageCharacteristics();
		
		festival = new StageFestival (stage.getFestival());

		
		
		}

		@Data
		@NoArgsConstructor
		public static class StageFestival{
			private Long festivalId;
			private String festivalName;
			private String festivalEdition;
			private String festivalCountry;
			private String festivalCity;
			private String festivalVenue;
			private String festivalTheme;
			
			public StageFestival(Festival festival) {
				festivalId=festival.getFestivalId();
				festivalName=festival.getFestivalName();
				festivalEdition=festival.getFestivalEdition();
				festivalCountry=festival.getFestivalCountry();
				festivalCity=festival.getFestivalCity();
				festivalVenue=festival.getFestivalVenue();
				festivalTheme=festival.getFestivalTheme();		
				}
		}
	
}
