package bg.softuni.towebarshopweb.repository.CarRepositories;

import bg.softuni.towebarshopweb.model.entity.CarEntities.Trim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrimRepository extends JpaRepository<Trim, Long> {
    List<Trim> findByModelIdAndSerieId(Long modelId, Long seriesId);
}
