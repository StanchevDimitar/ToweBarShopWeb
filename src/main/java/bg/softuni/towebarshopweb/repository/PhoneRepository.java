package bg.softuni.towebarshopweb.repository;

import bg.softuni.towebarshopweb.model.entity.PriorityPhoneNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<PriorityPhoneNumbers, Long> {

    List<PriorityPhoneNumbers> findAllByIsProcessedFalseOrderByInsertedTime();

    List<PriorityPhoneNumbers> findAllByIsProcessedTrueOrderByInsertedTime();

    @Modifying
    @Query("DELETE FROM PriorityPhoneNumbers e WHERE e.completedTime < :threeDaysAgoDate")
    void deleteOlderThan(@Param("threeDaysAgoDate") LocalDateTime threeDaysAgoDate);

}
