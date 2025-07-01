package music.festival.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.festival.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

}
