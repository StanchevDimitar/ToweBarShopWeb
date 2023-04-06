package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
