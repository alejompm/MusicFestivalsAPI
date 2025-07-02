package music.festival.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.festival.controller.model.FestivalStage.StageFestival;
import music.festival.entity.Artist;
import music.festival.entity.Festival;
import music.festival.entity.Stage;

@Data
@NoArgsConstructor
public class FestivalData {

	private Long festivalId;
	private String festivalName;
	private String festivalEdition;
	private String festivalCountry;
	private String festivalCity;
	private String festivalVenue;
	private String festivalTheme;
	
	private Set<FestivalArtist> artists= new HashSet <>();
	private Set<FestivalStage> stages = new HashSet<>();
	
	public FestivalData(Festival festival) {
		festivalId=festival.getFestivalId();
		festivalName=festival.getFestivalName();
		festivalEdition=festival.getFestivalEdition();
		festivalCountry=festival.getFestivalCountry();
		festivalCity=festival.getFestivalCity();
		festivalVenue=festival.getFestivalVenue();
		festivalTheme=festival.getFestivalTheme();
	
		for(Artist artist: festival.getArtists()) {
			artists.add(new FestivalArtist(artist));
		}
	
		for(Stage stage: festival.getStages()) {
			stages.add(new FestivalStage(stage));
		}
	}
		
		@Data
		@NoArgsConstructor
		static class FestivalArtist{
			private Long artistId;
			private String artistName;
			private String artistManager;
			private String artistEmail;
			private String artistPhone;
			private String artistSpecialRequirements;
			
			FestivalArtist(Artist artist) {
				artistId = artist.getArtistId();
				artistName=artist.getArtistName();
				artistManager=artist.getArtistManager();
				artistEmail=artist.getArtistEmail();
				artistPhone=artist.getArtistPhone();
				artistSpecialRequirements=artist.getArtistSpecialRequirements();
			}
		}

		@Data
		@NoArgsConstructor
		static class FestivalStage{
			private Long stageId;
			private String stageName;
			private String stageGenre;
			private Long stageCapacity;
			private String stageCharacteristics;
			
			FestivalStage(Stage stage) {
				stageId = stage.getStageId();
				stageName=stage.getStageName();
				stageGenre=stage.getStageGenre();
				stageCapacity=stage.getStageCapacity();
				stageCharacteristics =stage.getStageCharacteristics();
							
				}
		}
		
	}
	

	

