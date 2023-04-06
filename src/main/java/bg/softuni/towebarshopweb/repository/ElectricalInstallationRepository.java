package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.ElectricalInstallation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricalInstallationRepository extends JpaRepository<ElectricalInstallation, Long> {
}
