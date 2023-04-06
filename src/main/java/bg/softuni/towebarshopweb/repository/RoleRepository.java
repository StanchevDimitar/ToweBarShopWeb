package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.RoleEntity;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole(RoleNameEnum role);
}
