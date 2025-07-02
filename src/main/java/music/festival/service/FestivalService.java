package music.festival.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.festival.controller.model.FestivalArtist;
import music.festival.controller.model.FestivalData;
import music.festival.controller.model.FestivalStage;
import music.festival.dao.ArtistDao;
import music.festival.dao.FestivalDao;
import music.festival.dao.StageDao;
import music.festival.entity.Artist;
import music.festival.entity.Festival;
import music.festival.entity.Stage;


@Service
public class FestivalService {

	@Autowired
	private FestivalDao festivalDao;

	@Autowired
	private StageDao stageDao;
	
	@Autowired
	private ArtistDao artistDao;

	@Transactional(readOnly = false)
	public FestivalData saveFestival(FestivalData festivalData) {
		Long festivalId = festivalData.getFestivalId();
		Festival festival = findOrCreateFestival(festivalId);

		copyFestivalFields(festival, festivalData);
		return new FestivalData(festivalDao.save(festival));
	}

	private Festival findOrCreateFestival(Long festivalId) {
		Festival festival;

		if (Objects.isNull(festivalId)) {
			festival = new Festival();
		} else {
			festival = findFestivalById(festivalId);
		}

		return festival;
	}

	private Festival findFestivalById(Long festivalId) {
		return festivalDao.findById(festivalId)
				.orElseThrow(() -> new NoSuchElementException("Festival ID " + festivalId + " not found"));
	}

	private void copyFestivalFields(Festival festival, FestivalData festivalData) {

		festival.setFestivalName(festivalData.getFestivalName());
		festival.setFestivalEdition(festivalData.getFestivalEdition());
		festival.setFestivalCountry(festivalData.getFestivalCountry());
		festival.setFestivalCity(festivalData.getFestivalCity());
		festival.setFestivalVenue(festivalData.getFestivalVenue());
		festival.setFestivalTheme(festivalData.getFestivalTheme());

	}

	@Transactional(readOnly = false)
	public FestivalStage saveStage(Long festivalId, FestivalStage festivalStage) {
		Festival festival = findFestivalById(festivalId);
		Long stageId = festivalStage.getStageId();
		Stage stage = findOrCreateStage(festivalId, stageId);

		copyStageFields(stage, festivalStage);

		stage.setFestival(festival);

		festival.getStages().add(stage);

		Stage dbStage = stageDao.save(stage);

		return new FestivalStage(dbStage);
	}

	private void copyStageFields(Stage stage, FestivalStage festivalStage) {

		stage.setStageId(festivalStage.getStageId());
		stage.setStageName(festivalStage.getStageName());
		stage.setStageGenre(festivalStage.getStageGenre());
		stage.setStageCapacity(festivalStage.getStageCapacity());
		stage.setStageCharacteristics(festivalStage.getStageCharacteristics());
	}

	private Stage findOrCreateStage(Long festivalId, Long stageId) {
		Stage stage;

		if (Objects.isNull(stageId)) {
			stage = new Stage();
		} else {
			stage = findStageById(festivalId, stageId);
		}

		return stage;
	}

	private Stage findStageById(Long festivalId, Long stageId) {
		Stage stage = stageDao.findById(stageId)
				.orElseThrow(() -> new NoSuchElementException("Stage " + stageId + " not found"));

		if (stage.getFestival().getFestivalId() == festivalId) {
			return stage;
		} else {
			throw new IllegalArgumentException("Stage= " + stageId + " isn't a stage of the festival " + festivalId);

		}
	}

/*	@Transactional(readOnly=false)
	public FestivalArtist createArtist(FestivalArtist festivalArtist) {
		return festivalArtist;
	}*/
	
	@Transactional(readOnly=false)
	public FestivalArtist saveArtist(Long festivalId, FestivalArtist festivalArtist, boolean isUpdate) {

		boolean newArtist=true;
		
		Festival festival= findFestivalById(festivalId);
		
		Long artistId = festivalArtist.getArtistId();
		
		if (Objects.nonNull(artistId)) {
			newArtist=false;
		}
		
		Artist artist= findOrCreateArtist(festivalId, artistId, isUpdate);

		if (newArtist=true) {
		copyArtistFields(artist, festivalArtist);
		}
		
		// SINCE IT IS MANY TO MANY, I HAVE TO DO BOTH
		artist.getFestivals().add(festival);
		festival.getArtists().add(artist);

		Artist dbArtist = artistDao.save(artist);

		return new FestivalArtist(dbArtist);
	}

	private void copyArtistFields(Artist artist, FestivalArtist festivalArtist) {

		if(festivalArtist.getArtistId()!=null) {artist.setArtistId(festivalArtist.getArtistId());}
		if(festivalArtist.getArtistName()!=null) {artist.setArtistName(festivalArtist.getArtistName());}
		if(festivalArtist.getArtistManager()!=null) {artist.setArtistManager(festivalArtist.getArtistManager());}
		if(festivalArtist.getArtistEmail()!=null) {artist.setArtistEmail(festivalArtist.getArtistEmail());}
		if(festivalArtist.getArtistPhone()!=null) {artist.setArtistPhone(festivalArtist.getArtistPhone());}
		if(festivalArtist.getArtistSpecialRequirements()!=null) {artist.setArtistSpecialRequirements(festivalArtist.getArtistSpecialRequirements());}

	}

	private Artist findOrCreateArtist(Long festivalId, Long artistId, boolean isUpdate) {
		Artist artist;

		if (Objects.isNull(artistId)) {
			artist = new Artist();
		} else {
			artist = findArtistById(festivalId, artistId, isUpdate);
		}

		return artist;
	}

	private Artist findArtistById(Long festivalId, Long artistId, boolean isUpdate) {
		
		
		Artist artist = artistDao.findById(artistId)
				.orElseThrow(() -> new NoSuchElementException("Artist " + artistId + " not found"));

		if(!isUpdate) {
		return artist;
		}
		else {
		for (Festival festival: artist.getFestivals()) {
			if (festival.getFestivalId() == festivalId) {
				return artist;
			}
		}

		throw new IllegalArgumentException(
				"Artist= " + artistId + " is not a performing in the festival " + festivalId);
		}
	}

	@Transactional(readOnly=true)
	public List<FestivalData> retrieveAllFestivals() {
		List<Festival> festivals=festivalDao.findAll();
		
		List<FestivalData> result = new LinkedList<>();
		
		for (Festival festival:festivals) {
			FestivalData fest = new FestivalData(festival);
			
			fest.getStages().clear();
			fest.getArtists().clear();
			
			result.add(fest);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public FestivalData retrieveFestivalById(Long festivalId) {

		Festival festival = findFestivalById(festivalId);
		return new FestivalData(festival);
	}

	@Transactional(readOnly=true)
	public Set<FestivalStage> retrieveStages(Long festivalId) {
		Festival festival = findFestivalById(festivalId);
		FestivalData fest=new FestivalData(festival);
		Set<FestivalStage> festStages = new HashSet<>();
		
		for (Stage stage:festival.getStages()) {
			FestivalStage festStage = new FestivalStage(stage);

			festStages.add(festStage);
		}
		
		return festStages;

	}
	
	@Transactional(readOnly=true)
	public Set<FestivalArtist> retrieveArtistsByFestival(Long festivalId) {
		Festival festival = findFestivalById(festivalId);
		FestivalData fest=new FestivalData(festival);
		Set<FestivalArtist> festArtists = new HashSet<>();
		
		for (Artist artist:festival.getArtists()) {
			FestivalArtist festArtist = new FestivalArtist(artist);

			festArtist.getFestivals().clear();
			
			festArtists.add(festArtist);
		}
		
		return festArtists;
	}
	
	@Transactional(readOnly=true)
	public List<FestivalArtist> retrieveAllArtists() {
		List<Artist> artists=artistDao.findAll();
		
		List<FestivalArtist> result = new LinkedList<>();
		
		for (Artist artist:artists) {
			FestivalArtist festArt= new FestivalArtist(artist);
			
			festArt.getFestivals().clear();
			
			result.add(festArt);
		}
		return result;
	}

	public Map<String, String> deleteFestivalById(Long festivalId) {
		Festival festival= findFestivalById(festivalId);
		festivalDao.delete(festival);
		Map<String,String> result = new HashMap <>();
		 result.put("message", "Festival "+ festivalId + " has been deleted");
		return result;
	}
	
	
	
	

}
