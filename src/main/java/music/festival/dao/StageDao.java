package music.festival.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.festival.entity.Stage;

public interface StageDao extends JpaRepository<Stage, Long> {

}
