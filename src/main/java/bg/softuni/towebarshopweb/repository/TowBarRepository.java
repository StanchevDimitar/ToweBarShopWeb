package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.CarEntities.NewCar;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TowBarRepository extends JpaRepository<TowBar, Long> {
    TowBar findByName(String name);

    Optional<TowBar> findByTypeAndNewCar(TowBarType type, NewCar newCar);
}
