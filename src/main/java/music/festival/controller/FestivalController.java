package music.festival.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.festival.controller.model.FestivalArtist;
import music.festival.controller.model.FestivalData;
import music.festival.controller.model.FestivalStage;
import music.festival.entity.Festival;
import music.festival.service.FestivalService;

@RestController
@RequestMapping("/festival")
@Slf4j
public class FestivalController {
	
	@Autowired
	private FestivalService festivalService;
	
	@PostMapping("/")
	@ResponseStatus(code= HttpStatus.CREATED)
	public FestivalData insertPetStore(@RequestBody FestivalData festivalData) {
		log.info("Creating Festival {}",festivalData); // {} is a replaceable parameter that is to be changed with contData
	
		
		return festivalService.saveFestival(festivalData);
		
	}
	
	@PutMapping("/{festivalId}")
	public FestivalData updateFestival(@PathVariable Long festivalId, @RequestBody FestivalData festivalData) {
		festivalData.setFestivalId(festivalId);
		
		log.info("Modifying Festival with ID={} and Data= {}",festivalId,festivalData); 	
		return festivalService.saveFestival(festivalData);
	}
	
	@PostMapping("/{festivalId}/stage")
	@ResponseStatus(code=HttpStatus.CREATED)
	public FestivalStage insertStage(@PathVariable Long festivalId, @RequestBody FestivalStage festivalStage) {
		log.info("Creating stage {} for festival with ID={}",festivalStage,festivalId);
	
	return festivalService.saveStage(festivalId,festivalStage);
	}
	
	@PutMapping("/{festivalId}/stage/{stageId}")
	public FestivalStage updateStage(@PathVariable Long festivalId, @PathVariable Long stageId, @RequestBody FestivalStage festivalStage) {
		log.info("Updating stage {} for festival with ID={}",festivalStage,festivalId);
	
		festivalStage.setStageId(stageId);
	return festivalService.saveStage(festivalId,festivalStage);
	}
	
	
/*	@PostMapping("/artist")
	@ResponseStatus(code=HttpStatus.CREATED)
	public FestivalArtist insertArtist (@RequestBody FestivalArtist festivalArtist) {
		log.info("Creating artist {}",festivalArtist);
	
	return festivalService.createArtist(festivalArtist);
	}	*/
	
	@PostMapping("/{festivalId}/artist")
	@ResponseStatus(code=HttpStatus.CREATED)
	public FestivalArtist insertArtist (@PathVariable Long festivalId, @RequestBody FestivalArtist festivalArtist) {
		log.info("Creating artist {} for festival with ID={}",festivalArtist,festivalId);
		boolean isUpdate=false;
	return festivalService.saveArtist(festivalId,festivalArtist,isUpdate);
	}
	
	
	@PostMapping("/{festivalId}/artist/{artistId}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public FestivalArtist insertArtist (@PathVariable Long festivalId,@PathVariable Long artistId) {
		FestivalArtist festivalArtist = new FestivalArtist();
		festivalArtist.setArtistId(artistId);
		log.info("Inserting artist {} for festival with ID={}",festivalArtist,festivalId);
		boolean isUpdate=false;
	return festivalService.saveArtist(festivalId,festivalArtist,isUpdate);
	}
	
	@PutMapping("/{festivalId}/artist/{artistId}")
	public FestivalArtist updateArtist (@PathVariable Long festivalId,@PathVariable Long artistId, @RequestBody FestivalArtist festivalArtist) {
		
		festivalArtist.setArtistId(artistId);
		
		log.info("Updating artist {}  with ID={}",festivalArtist,artistId);
		boolean isUpdate=true;
	return festivalService.saveArtist(festivalId,festivalArtist,isUpdate);
	}
	
	@GetMapping("/")
	public List<FestivalData> retrieveAllFestivals(){
		return festivalService.retrieveAllFestivals();
	}
	
	@GetMapping("/{festivalId}")
	public FestivalData retrieveFestivalById(@PathVariable Long festivalId){
		return festivalService.retrieveFestivalById(festivalId);
	}
	
	@GetMapping("/{festivalId}/stages")
	public Set<FestivalStage> retrieveStages(@PathVariable Long festivalId){
		return festivalService.retrieveStages(festivalId);
	}
	
	@GetMapping("/{festivalId}/artists")
	public Set<FestivalArtist> retrieveArtists(@PathVariable Long festivalId){
		return festivalService.retrieveArtistsByFestival(festivalId);
	}
	
	@GetMapping("/artists")
	public List<FestivalArtist> retrieveAllArtists(){
		return festivalService.retrieveAllArtists();
	}

	@DeleteMapping("/{festivalId}")
	public Map<String, String> deleteFestivalById(@PathVariable Long festivalId){
		log.info("Deleting festival with ID={}",festivalId);
		return festivalService.deleteFestivalById(festivalId);
	}
	

}
