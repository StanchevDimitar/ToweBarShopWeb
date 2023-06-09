package bg.softuni.towebarshopweb.repository.CarRepositories;

import bg.softuni.towebarshopweb.model.entity.CarEntities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {
    List<Model> findByMakeId(Long makeId);
}
