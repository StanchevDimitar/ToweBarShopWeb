package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.CarEntities.NewCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<NewCar, Long> {

    Optional<NewCar> findByMakeAndYearAndModelAndGenerationAndBody(String make, String year, String model, String generation, String body);
}
