package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
