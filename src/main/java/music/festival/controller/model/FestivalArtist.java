package music.festival.controller.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import music.festival.entity.Artist;
import music.festival.entity.Festival;

@Data
@NoArgsConstructor
public class FestivalArtist {

	private Long artistId;
	private String artistName;
	private String artistManager;
	private String artistEmail;
	private String artistPhone;
	private String artistSpecialRequirements;
	private Set<Festival> festival = new HashSet <>();
	private Set<ArtistFestival> festivals = new HashSet <>();
	
	public FestivalArtist(Artist artist) {
		artistId = artist.getArtistId();
		artistName=artist.getArtistName();
		artistManager=artist.getArtistManager();
		artistEmail=artist.getArtistEmail();
		artistPhone=artist.getArtistPhone();
		artistSpecialRequirements=artist.getArtistSpecialRequirements();
		
		for(Festival festival: artist.getFestivals()) {
			festivals.add(new ArtistFestival(festival));
		}
		}
	
	@Data
	@NoArgsConstructor
	public static class ArtistFestival{
		private Long festivalId;
		private String festivalName;
		private String festivalEdition;
		private String festivalCountry;
		private String festivalCity;
		private String festivalVenue;
		private String festivalTheme;
		
		public ArtistFestival(Festival festival) {
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
