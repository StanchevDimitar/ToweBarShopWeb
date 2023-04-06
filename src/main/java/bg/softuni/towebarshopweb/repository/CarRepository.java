package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.CarEntities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId(Long make_id, Long model_id, Long generation_id, Long serie_id, Long trim_id);

}
