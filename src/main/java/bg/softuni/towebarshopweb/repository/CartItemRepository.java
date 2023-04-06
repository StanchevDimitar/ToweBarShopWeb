package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<List<CartItem>> findAllByUser(UserEntity user);


}
