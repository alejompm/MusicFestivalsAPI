package music.festival.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.festival.entity.Festival;

public interface FestivalDao extends JpaRepository<Festival, Long> {

}
