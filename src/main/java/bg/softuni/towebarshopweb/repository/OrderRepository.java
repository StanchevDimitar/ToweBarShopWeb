package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByIsFinishedTrueAndIsSentFalseOrderByCompletedTime();

    List<Order> findAllByIsFinishedTrueAndIsSentTrueOrderByCompletedTime();
    @Modifying
    @Query("DELETE FROM Order e WHERE e.sentTime < :threeDaysAgoDate")
    void deleteOlderThan(@Param("threeDaysAgoDate") LocalDateTime threeDaysAgoDate);

}
