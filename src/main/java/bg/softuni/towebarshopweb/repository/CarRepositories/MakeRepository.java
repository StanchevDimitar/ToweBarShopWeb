package bg.softuni.towebarshopweb.repository.CarRepositories;

import bg.softuni.towebarshopweb.model.entity.CarEntities.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface MakeRepository extends JpaRepository<Make, Long> {
    Optional<Make> findByName(String make);
}
