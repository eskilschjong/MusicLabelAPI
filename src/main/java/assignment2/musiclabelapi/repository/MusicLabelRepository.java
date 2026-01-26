package assignment2.musiclabelapi.repository;

import assignment2.musiclabelapi.model.MusicLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicLabelRepository extends JpaRepository<MusicLabel, Long> {
}