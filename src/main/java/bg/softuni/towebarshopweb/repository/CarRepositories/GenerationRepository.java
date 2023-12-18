package bg.softuni.towebarshopweb.repository.CarRepositories;

import bg.softuni.towebarshopweb.model.entity.CarEntities.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenerationRepository extends JpaRepository<Generation,Long> {
    List<Generation> findByModelId(Long modelId);

    Optional<Generation> findByNameAndYear(String generation, String year);

    Optional<Generation> findByName(String generation);
}
