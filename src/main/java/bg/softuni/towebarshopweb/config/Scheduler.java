package bg.softuni.towebarshopweb.config;

import bg.softuni.towebarshopweb.repository.OrderRepository;
import bg.softuni.towebarshopweb.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Scheduler {

    private final PhoneRepository phoneRepository;
    private final OrderRepository orderRepository;

    public Scheduler(PhoneRepository phoneRepository, OrderRepository orderRepository) {
        this.phoneRepository = phoneRepository;
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedRate = 86_400)
    @Transactional
    public void phoneNumbersCleaner() {
        LocalDateTime threeDaysAgoDate = LocalDateTime.now().minusDays(60);

        phoneRepository.deleteOlderThan(threeDaysAgoDate);
    }
    @Scheduled(fixedRate = 86_400)
    @Transactional
    public void ordersCleaner() {
        LocalDateTime threeDaysAgoDate = LocalDateTime.now().minusDays(60);

        orderRepository.deleteOlderThan(threeDaysAgoDate);
    }

}
